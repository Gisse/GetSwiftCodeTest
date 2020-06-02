package com.djevtic.myswifttestcode.network

import com.djevtic.myswifttestcode.BuildConfig
import com.djevtic.myswifttestcode.extensions.ioToMain
import com.djevtic.myswifttestcode.network.models.leagueteams.LeagueTeamsDataModel
import com.djevtic.myswifttestcode.network.models.player.PlayerDataModel
import com.djevtic.myswifttestcode.network.models.playersinteam.PlayersInTeamDataModel
import com.djevtic.myswifttestcode.network.models.standing.StandingResponseModelData
import io.reactivex.Single
import retrofit2.Response

interface SwiftUsecase {
    fun getLeagueTeamsData(leagueId: Int): Single<Response<LeagueTeamsDataModel>>
    fun getPlayerData(playerId: Int): Single<Response<PlayerDataModel>>
    fun getLeagueStanding(leagueId: Int): Single<Response<StandingResponseModelData>>
    fun getPlayersInTeam(teamId: Int, timePeriod: String): Single<Response<PlayersInTeamDataModel>>
    fun getPlayersInSquad(
        squadId: Int,
        timePeriod: String
    ): Single<Response<PlayersInTeamDataModel>>
}

class SwiftUsecaseImpl(private val apiSwiftInterface: ApiSwiftInterface) : SwiftUsecase {
    override fun getLeagueTeamsData(leagueId: Int): Single<Response<LeagueTeamsDataModel>> {
        return apiSwiftInterface.getLeagueTeamsData(
            leagueId,
            BuildConfig.SWIFT_API_HOST,
            BuildConfig.SWIFT_API_KEY
        ).ioToMain()
    }

    override fun getPlayerData(playerId: Int): Single<Response<PlayerDataModel>> {
        return apiSwiftInterface.getPlayerData(
            playerId,
            BuildConfig.SWIFT_API_HOST,
            BuildConfig.SWIFT_API_KEY
        ).ioToMain()
    }

    override fun getLeagueStanding(leagueId: Int): Single<Response<StandingResponseModelData>> {
        return apiSwiftInterface.getLeagueStanding(
            leagueId,
            BuildConfig.SWIFT_API_HOST,
            BuildConfig.SWIFT_API_KEY
        ).ioToMain()
    }

    override fun getPlayersInTeam(
        teamId: Int,
        timePeriod: String
    ): Single<Response<PlayersInTeamDataModel>> {
        return apiSwiftInterface.getPlayersInTeam(
            teamId,
            timePeriod,
            BuildConfig.SWIFT_API_HOST,
            BuildConfig.SWIFT_API_KEY
        ).ioToMain()
    }

    override fun getPlayersInSquad(
        squadId: Int,
        timePeriod: String
    ): Single<Response<PlayersInTeamDataModel>> {
        return apiSwiftInterface.getPlayersInTeam(
            squadId,
            timePeriod,
            BuildConfig.SWIFT_API_HOST,
            BuildConfig.SWIFT_API_KEY
        ).ioToMain()
    }

}