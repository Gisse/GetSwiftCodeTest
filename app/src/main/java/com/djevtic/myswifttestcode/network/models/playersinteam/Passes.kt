package com.djevtic.myswifttestcode.network.models.playersinteam


import com.google.gson.annotations.SerializedName

data class Passes(
    @SerializedName("accuracy")
    val accuracyPasses: Int,
    @SerializedName("key")
    val keyPasses: Int,
    @SerializedName("total")
    val totalPasses: Int
)