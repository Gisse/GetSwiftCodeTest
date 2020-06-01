package com.djevtic.myswifttestcode.data

import android.util.Log
import com.djevtic.myswifttestcode.App
import com.djevtic.myswifttestcode.extensions.ioToMain
import com.djevtic.myswifttestcode.network.ApiService
import com.djevtic.myswifttestcode.network.ApiSwiftInterface
import com.djevtic.myswifttestcode.network.SwiftUsecaseImpl
import com.djevtic.myswifttestcode.network.models.playersinteam.Player
import com.djevtic.myswifttestcode.network.models.standing.Standing
import com.djevtic.myswifttestcode.presentation.team.TeamActivity.Companion.ATTACKER
import com.djevtic.myswifttestcode.presentation.team.TeamActivity.Companion.DEFENDER
import com.djevtic.myswifttestcode.presentation.team.TeamActivity.Companion.GOALKEEPER
import com.djevtic.myswifttestcode.presentation.team.TeamActivity.Companion.MIDFIELDER
import com.djevtic.myswifttestcode.utils.TimeUtils
import io.reactivex.Single

object DataManager {

    private val swiftUsecase =
        SwiftUsecaseImpl(ApiService.getSwiftClient().create(ApiSwiftInterface::class.java))

    private fun getStandingDataFromNetworkOrDatabase(leagueId: Int, lastUpdateTime: Long): Single<List<Standing>> {
        var database = App.getDatabase()
        if (TimeUtils.didDayPassed(lastUpdateTime)) {
            return swiftUsecase.getLeagueStanding(leagueId)
                .map {
                    var standingList: List<Standing> = arrayListOf()
                    it.body()?.api?.standings?.let { standing ->
                        standingList = standing[0]
                    }
                    var time = System.currentTimeMillis()
                    standingList.forEach {
                        it.dataUpdate = time
                        it.leagueId = leagueId
                    }
                    return@map standingList
                }.doOnSuccess {
                    var database = App.getDatabase()
                    database.standingDao().insertAll(it).ioToMain().subscribe(
                        {}
                        ,
                        {}
                    )
                }
        } else {
            return database.standingDao().getAll()
        }
    }

    fun getStandingsFromDatabase(leagueId: Int) : Single<List<Standing>> {
        var database = App.getDatabase()
        return database.standingDao().getAll()
    }

    fun getStandingsData(leagueId: Int): Single<List<Standing>> {
        var database = App.getDatabase()
        return database.standingDao().getLastUpdateTimestamp(leagueId)
            .flatMap {
                getStandingDataFromNetworkOrDatabase(leagueId, it)
            }.onErrorResumeNext {
                getStandingDataFromNetworkOrDatabase(leagueId, 0)
            }
    }



    fun getPlayersData(teamId: Int): Single<List<Player>> {
        var database = App.getDatabase()
        return database.playerDao().getPlayersLastUpdateTimestamp(teamId)
            .flatMap {
                getPlayersDataFromNetworkOrDatabase(teamId, it)
            }.onErrorResumeNext {
                getPlayersDataFromNetworkOrDatabase(teamId, 0)
            }
    }

    private fun getPlayersDataFromNetworkOrDatabase(teamId: Int, lastUpdateTime: Long): Single<List<Player>> {
        var database = App.getDatabase()
        if (TimeUtils.didDayPassed(lastUpdateTime)) {
            return swiftUsecase.getPlayersInTeam(teamId, "2018-2019")
                .map {
                    var playerList: List<Player> = arrayListOf()
                    it.body()?.api?.players?.let { players ->
                        playerList = preparePlayerList(players.filter { it.league == "Premier League" })
                    }
                    val time = System.currentTimeMillis()
                    playerList.forEach {
                        it.dataUpdate = time
                    }
                    return@map playerList
                }
                .doOnSuccess {
                    database.playerDao().insertAll(it).ioToMain().subscribe(
                        {
                            Log.d("djevtic", "data")
                        }
                    ,
                        {
                            Log.d("djevtic", "data")
                        }
                    )
                }
        } else {
            return database.playerDao().getAllPlayersForTeam(teamId)
        }
    }

    /**
     * Prepare list of players in team in required order
     */
    fun preparePlayerList(players :List<Player>) : List<Player> {
        var playerList : MutableList<Player> = mutableListOf()
        playerList.addAll(players.filter { it.position == GOALKEEPER })
        playerList.addAll(players.filter { it.position == DEFENDER })
        playerList.addAll(players.filter { it.position == MIDFIELDER })
        playerList.addAll(players.filter { it.position == ATTACKER })
        return playerList
    }

    fun getPlayersDataFromDatabase(teamId: Int) : Single<List<Player>> {
        var database = App.getDatabase()
        return database.playerDao().getAllPlayersForTeam(teamId)
    }
}