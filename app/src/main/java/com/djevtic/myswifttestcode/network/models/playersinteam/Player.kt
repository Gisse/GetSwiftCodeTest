package com.djevtic.myswifttestcode.network.models.playersinteam


import com.google.gson.annotations.SerializedName

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
    val cards: Cards,
    @SerializedName("dribbles")
    val dribbles: Dribbles,
    @SerializedName("duels")
    val duels: Duels,
    @SerializedName("firstname")
    val firstname: String,
    @SerializedName("fouls")
    val fouls: Fouls,
    @SerializedName("games")
    val games: Games,
    @SerializedName("goals")
    val goals: Goals,
    @SerializedName("height")
    val height: String,
    @SerializedName("injured")
    val injured: Any,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("league")
    val league: String,
    @SerializedName("nationality")
    val nationality: String,
    @SerializedName("number")
    val number: Any,
    @SerializedName("passes")
    val passes: Passes,
    @SerializedName("penalty")
    val penalty: Penalty,
    @SerializedName("player_id")
    val playerId: Int,
    @SerializedName("player_name")
    val playerName: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("rating")
    val rating: Any,
    @SerializedName("season")
    val season: String,
    @SerializedName("shots")
    val shots: Shots,
    @SerializedName("substitutes")
    val substitutes: Substitutes,
    @SerializedName("tackles")
    val tackles: Tackles,
    @SerializedName("team_id")
    val teamId: Int,
    @SerializedName("team_name")
    val teamName: String,
    @SerializedName("weight")
    val weight: String
)