package com.djevtic.myswifttestcode.network.models.player


import com.google.gson.annotations.SerializedName

data class Api(
    @SerializedName("players")
    val players: List<Player>,
    @SerializedName("results")
    val results: Int
)