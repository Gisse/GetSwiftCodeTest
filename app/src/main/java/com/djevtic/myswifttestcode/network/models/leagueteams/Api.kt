package com.djevtic.myswifttestcode.network.models.leagueteams


import com.google.gson.annotations.SerializedName

data class Api(
    @SerializedName("results")
    val results: Int,
    @SerializedName("teams")
    val teams: List<Team>
)