package com.djevtic.myswifttestcode.network.models.playersinteam


import com.google.gson.annotations.SerializedName

data class Tackles(
    @SerializedName("blocks")
    val blocks: Int,
    @SerializedName("interceptions")
    val interceptions: Int,
    @SerializedName("total")
    val total: Int
)