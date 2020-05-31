package com.djevtic.myswifttestcode.network.models.playersinteam


import com.google.gson.annotations.SerializedName

data class Games(
    @SerializedName("appearences")
    val appearencesGames: Int,
    @SerializedName("lineups")
    val lineupsGames: Int,
    @SerializedName("minutes_played")
    val minutesPlayedGames: Int
)