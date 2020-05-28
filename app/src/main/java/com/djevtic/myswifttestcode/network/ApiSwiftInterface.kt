package com.djevtic.myswifttestcode.network

import com.djevtic.myswifttestcode.network.models.leagueteams.LeagueTeamsDataModel
import com.djevtic.myswifttestcode.network.models.player.PlayerDataModel
import com.djevtic.myswifttestcode.network.models.playersinteam.PlayersInTeamDataModel
import com.djevtic.myswifttestcode.network.models.standing.StandingResponseModelData
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiSwiftInterface {

    @GET("v2/teams/league/{league_id}")
    fun getLeagueTeamsData(
        @Path("league_id") leagueId: Int,
        @Header("x-rapidapi-host") host: String,
        @Header("x-rapidapi-key") apiKey: String
    ): Single<Response<LeagueTeamsDataModel>>

    @GET("v2/players/player/{player_id}")
    fun getPlayerData(
        @Path("player_id") playerId: Int,
        @Header("x-rapidapi-host") host: String,
        @Header("x-rapidapi-key") apiKey: String
    ): Single<Response<PlayerDataModel>>

    @GET("v2/leagueTable/{league_id}")
    fun getLeagueStanding(
        @Path("league_id") leagueId: Int,
        @Header("x-rapidapi-host") host: String,
        @Header("x-rapidapi-key") apiKey: String
    ): Single<Response<StandingResponseModelData>>

    @GET("v2/players/team/{team_id}/{time_period}")
    fun getPlayersInTeam(
        @Path("team_id") teamId: Int,
        @Path("time_period") timePeriod: String,
        @Header("x-rapidapi-host") host: String,
        @Header("x-rapidapi-key") apiKey: String
    ): Single<Response<PlayersInTeamDataModel>>

    @GET("v2/players/squad/{squad_id}/{time_period}")
    fun getPlayersInSquad(
        @Path("squad_id") squadId: Int,
        @Path("time_period") timePeriod: String,
        @Header("x-rapidapi-host") host: String,
        @Header("x-rapidapi-key") apiKey: String
    ): Single<Response<Any>>

}