package com.djevtic.myswifttestcode.network.models.standing


import com.google.gson.annotations.SerializedName

data class StandingResponseModelData(
    @SerializedName("api")
    val api: Api
)