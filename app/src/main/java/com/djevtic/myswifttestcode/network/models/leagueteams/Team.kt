package com.djevtic.myswifttestcode.network.models.leagueteams


import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("code")
    val code: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("founded")
    val founded: Int,
    @SerializedName("is_national")
    val isNational: Boolean,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("team_id")
    val teamId: Int,
    @SerializedName("venue_address")
    val venueAddress: String,
    @SerializedName("venue_capacity")
    val venueCapacity: Int,
    @SerializedName("venue_city")
    val venueCity: String,
    @SerializedName("venue_name")
    val venueName: String,
    @SerializedName("venue_surface")
    val venueSurface: String
)