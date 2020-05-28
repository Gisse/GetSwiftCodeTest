package com.djevtic.myswifttestcode.network.models.player


import com.google.gson.annotations.SerializedName

data class Shots(
    @SerializedName("on")
    val on: Int,
    @SerializedName("total")
    val total: Int
)