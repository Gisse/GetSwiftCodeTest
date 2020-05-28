package com.djevtic.myswifttestcode.network.models.standing


import com.google.gson.annotations.SerializedName

data class Api(
    @SerializedName("results")
    val results: Int,
    @SerializedName("standings")
    val standings: List<List<Standing>>
)