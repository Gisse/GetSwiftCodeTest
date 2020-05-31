package com.djevtic.myswifttestcode.network.models.playersinteam


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "player")
data class Player(
    @SerializedName("age")
    val age: Int,
    @SerializedName("birth_country")
    val birthCountry: String,
    @SerializedName("birth_date")
    val birthDate: String,
    @SerializedName("birth_place")
    val birthPlace: String,
    @SerializedName("captain")
    val captain: Int,
    @SerializedName("cards")
    @Embedded
    val cards: Cards,
    @SerializedName("dribbles")
    @Embedded
    val dribbles: Dribbles,
    @SerializedName("duels")
    @Embedded
    val duels: Duels,
    @SerializedName("firstname")
    val firstname: String,
    @SerializedName("fouls")
    @Embedded
    val fouls: Fouls,
    @SerializedName("games")
    @Embedded
    val games: Games,
    @SerializedName("goals")
    @Embedded
    val goals: Goals,
    @SerializedName("height")
    val height: String,
    @SerializedName("injured")
    val injured: String,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("league")
    val league: String,
    @SerializedName("nationality")
    val nationality: String,
    @SerializedName("number")
    val number: String,
    @SerializedName("passes")
    @Embedded
    val passes: Passes,
    @SerializedName("penalty")
    @Embedded
    val penalty: Penalty,
    @PrimaryKey
    @SerializedName("player_id")
    val playerId: Int,
    @SerializedName("player_name")
    val playerName: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("season")
    val season: String,
    @SerializedName("shots")
    @Embedded
    val shots: Shots,
    @SerializedName("substitutes")
    @Embedded
    val substitutes: Substitutes,
    @SerializedName("tackles")
    @Embedded
    val tackles: Tackles,
    @SerializedName("team_id")
    val teamId: Int,
    @SerializedName("team_name")
    val teamName: String,
    @SerializedName("weight")
    val weight: String,
    var dataUpdate : Long?
)