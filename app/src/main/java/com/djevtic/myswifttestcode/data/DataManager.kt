package com.djevtic.myswifttestcode.data

import com.djevtic.myswifttestcode.App
import com.djevtic.myswifttestcode.network.ApiService
import com.djevtic.myswifttestcode.network.ApiSwiftInterface
import com.djevtic.myswifttestcode.network.SwiftUsecaseImpl
import com.djevtic.myswifttestcode.network.models.playersinteam.Player
import com.djevtic.myswifttestcode.network.models.standing.Standing
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
                    return@map standingList
                }.doOnSuccess {
                    database.standingDao().insertAll(it)
                }
        } else {
            return database.standingDao().getAll()
        }
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
                        playerList = prepareList(players.filter { it.league == "Premier League" })
                    }
                    return@map playerList
                }.doOnSuccess {
                    database.playerDao().insertAll(it)
                }
        } else {
            return database.playerDao().getAllPlayersForTeam(teamId)
        }
    }

    fun prepareList(players :List<Player>) : List<Player> {
        var playerList : MutableList<Player> = mutableListOf()
        playerList.addAll(players.filter { it.position == "Goalkeeper" })
        playerList.addAll(players.filter { it.position == "Defender" })
        playerList.addAll(players.filter { it.position == "Midfielder" })
        playerList.addAll(players.filter { it.position == "Attacker" })
        return playerList
    }
}