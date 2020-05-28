package com.djevtic.myswifttestcode.network.models.playersinteam


import com.google.gson.annotations.SerializedName

data class Passes(
    @SerializedName("accuracy")
    val accuracy: Int,
    @SerializedName("key")
    val key: Int,
    @SerializedName("total")
    val total: Int
)