package com.djevtic.myswifttestcode.network.models.playersinteam


import com.google.gson.annotations.SerializedName

data class Penalty(
    @SerializedName("commited")
    val commited: Int,
    @SerializedName("missed")
    val missed: Int,
    @SerializedName("saved")
    val saved: Int,
    @SerializedName("success")
    val success: Int,
    @SerializedName("won")
    val won: Int
)