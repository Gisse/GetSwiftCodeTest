package com.djevtic.myswifttestcode.network.models.playersinteam


import com.google.gson.annotations.SerializedName

data class Shots(
    @SerializedName("on")
    val onShots: Int,
    @SerializedName("total")
    val totalShots: Int
)