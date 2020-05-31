package com.djevtic.myswifttestcode.network.models.standing

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "standing")
data class Standing(
    @SerializedName("all")
    @Embedded
    val all: All,
    @SerializedName("away")
    @Embedded
    val away: Away,
    @SerializedName("description")
    val description: String?,
    @SerializedName("forme")
    val forme: String?,
    @SerializedName("goalsDiff")
    val goalsDiff: Int?,
    @SerializedName("group")
    val group: String?,
    @SerializedName("home")
    @Embedded
    val home: Home,
    @SerializedName("lastUpdate")
    val lastUpdate: String?,
    @SerializedName("logo")
    val logo: String?,
    @SerializedName("points")
    val points: Int?,
    @SerializedName("rank")
    val rank: Int?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("team_id")
    @PrimaryKey
    val teamId: Int,
    @SerializedName("teamName")
    val teamName: String?,
    var leagueId : Int?,
    var dataUpdate : Long?
)