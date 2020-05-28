package com.djevtic.myswifttestcode.network.models.playersinteam


import com.google.gson.annotations.SerializedName

data class PlayersInTeamDataModel(
    @SerializedName("api")
    val api: Api
)