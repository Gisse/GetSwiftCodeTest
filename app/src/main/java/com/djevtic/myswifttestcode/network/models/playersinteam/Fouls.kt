package com.djevtic.myswifttestcode.network.models.playersinteam


import com.google.gson.annotations.SerializedName

data class Fouls(
    @SerializedName("committed")
    val committedFouls: Int,
    @SerializedName("drawn")
    val drawnFouls: Int
)