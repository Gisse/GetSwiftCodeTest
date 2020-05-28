package com.djevtic.myswifttestcode.network.models.playersinteam


import com.google.gson.annotations.SerializedName

data class Dribbles(
    @SerializedName("attempts")
    val attempts: Int,
    @SerializedName("success")
    val success: Int
)