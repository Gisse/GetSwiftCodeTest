package com.djevtic.myswifttestcode.network.models.standing


import com.google.gson.annotations.SerializedName

data class Standing(
    @SerializedName("all")
    val all: All,
    @SerializedName("away")
    val away: Away,
    @SerializedName("description")
    val description: String,
    @SerializedName("forme")
    val forme: String,
    @SerializedName("goalsDiff")
    val goalsDiff: Int,
    @SerializedName("group")
    val group: String,
    @SerializedName("home")
    val home: Home,
    @SerializedName("lastUpdate")
    val lastUpdate: String,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("points")
    val points: Int,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("team_id")
    val teamId: Int,
    @SerializedName("teamName")
    val teamName: String
)