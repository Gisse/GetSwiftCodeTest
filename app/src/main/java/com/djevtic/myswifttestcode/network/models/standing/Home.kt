package com.djevtic.myswifttestcode.network.models.standing


import com.google.gson.annotations.SerializedName

data class Home(
    @SerializedName("draw")
    val drawHome: Int?,
    @SerializedName("goalsAgainst")
    val goalsAgainstHome: Int?,
    @SerializedName("goalsFor")
    val goalsForHome: Int?,
    @SerializedName("lose")
    val loseHome: Int?,
    @SerializedName("matchsPlayed")
    val matchsPlayedHome: Int?,
    @SerializedName("win")
    val winHome: Int?
)