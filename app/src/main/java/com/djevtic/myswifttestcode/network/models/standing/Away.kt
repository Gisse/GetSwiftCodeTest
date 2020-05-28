package com.djevtic.myswifttestcode.network.models.standing


import com.google.gson.annotations.SerializedName

data class Away(
    @SerializedName("draw")
    val draw: Int,
    @SerializedName("goalsAgainst")
    val goalsAgainst: Int,
    @SerializedName("goalsFor")
    val goalsFor: Int,
    @SerializedName("lose")
    val lose: Int,
    @SerializedName("matchsPlayed")
    val matchsPlayed: Int,
    @SerializedName("win")
    val win: Int
)