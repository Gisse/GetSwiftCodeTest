package com.djevtic.myswifttestcode.network.models.player


import com.google.gson.annotations.SerializedName

data class Dribbles(
    @SerializedName("attempts")
    val attempts: Int,
    @SerializedName("success")
    val success: Int
)