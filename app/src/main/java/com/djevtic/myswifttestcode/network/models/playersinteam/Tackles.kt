package com.djevtic.myswifttestcode.network.models.playersinteam


import com.google.gson.annotations.SerializedName

data class Tackles(
    @SerializedName("blocks")
    val blocksTackles: Int,
    @SerializedName("interceptions")
    val interceptionsTackles: Int,
    @SerializedName("total")
    val totalTackles: Int
)