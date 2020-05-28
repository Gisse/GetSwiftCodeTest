package com.djevtic.myswifttestcode.network.models.playersinteam


import com.google.gson.annotations.SerializedName

data class Games(
    @SerializedName("appearences")
    val appearences: Int,
    @SerializedName("lineups")
    val lineups: Int,
    @SerializedName("minutes_played")
    val minutesPlayed: Int
)