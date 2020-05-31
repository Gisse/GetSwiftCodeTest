package com.djevtic.myswifttestcode.network.models.standing

import com.google.gson.annotations.SerializedName

data class All(
    @SerializedName("draw")
    val drawAll: Int?,
    @SerializedName("goalsAgainst")
    val goalsAgainstAll: Int?,
    @SerializedName("goalsFor")
    val goalsForAll: Int?,
    @SerializedName("lose")
    val loseAll: Int?,
    @SerializedName("matchsPlayed")
    val matchsPlayedAll: Int?,
    @SerializedName("win")
    val winAll: Int?
)