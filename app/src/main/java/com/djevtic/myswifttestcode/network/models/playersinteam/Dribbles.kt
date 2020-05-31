package com.djevtic.myswifttestcode.network.models.playersinteam


import com.google.gson.annotations.SerializedName

data class Dribbles(
    @SerializedName("attempts")
    val attemptsDribbles: Int,
    @SerializedName("success")
    val successDribbles: Int
)