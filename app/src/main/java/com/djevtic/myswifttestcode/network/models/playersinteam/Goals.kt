package com.djevtic.myswifttestcode.network.models.playersinteam


import com.google.gson.annotations.SerializedName

data class Goals(
    @SerializedName("assists")
    val assistsGoals: Int,
    @SerializedName("conceded")
    val concededGoals: Int,
    @SerializedName("total")
    val totalGoals: Int
)