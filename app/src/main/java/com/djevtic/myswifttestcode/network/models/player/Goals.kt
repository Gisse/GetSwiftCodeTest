package com.djevtic.myswifttestcode.network.models.player


import com.google.gson.annotations.SerializedName

data class Goals(
    @SerializedName("assists")
    val assists: Int,
    @SerializedName("conceded")
    val conceded: Int,
    @SerializedName("total")
    val total: Int
)