package com.djevtic.myswifttestcode.network.models.standing


import com.google.gson.annotations.SerializedName

data class Away(
    @SerializedName("draw")
    val drawAway: Int?,
    @SerializedName("goalsAgainst")
    val goalsAgainstAway: Int?,
    @SerializedName("goalsFor")
    val goalsForAway: Int?,
    @SerializedName("lose")
    val loseAway: Int?,
    @SerializedName("matchsPlayed")
    val matchsPlayedAway: Int?,
    @SerializedName("win")
    val winAway: Int?
)