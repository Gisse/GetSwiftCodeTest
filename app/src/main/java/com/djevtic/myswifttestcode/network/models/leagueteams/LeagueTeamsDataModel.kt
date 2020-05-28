package com.djevtic.myswifttestcode.network.models.leagueteams


import com.google.gson.annotations.SerializedName

data class LeagueTeamsDataModel(
    @SerializedName("api")
    val api: Api
)