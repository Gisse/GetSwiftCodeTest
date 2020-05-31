package com.djevtic.myswifttestcode.network.models.playersinteam


import com.google.gson.annotations.SerializedName

data class Duels(
    @SerializedName("total")
    val totalDuels: Int,
    @SerializedName("won")
    val wonDuels: Int
)