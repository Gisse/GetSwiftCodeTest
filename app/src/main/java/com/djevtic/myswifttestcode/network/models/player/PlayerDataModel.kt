package com.djevtic.myswifttestcode.network.models.player


import com.google.gson.annotations.SerializedName

data class PlayerDataModel(
    @SerializedName("api")
    val api: Api
)