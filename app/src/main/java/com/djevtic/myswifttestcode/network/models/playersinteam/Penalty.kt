package com.djevtic.myswifttestcode.network.models.playersinteam


import com.google.gson.annotations.SerializedName

data class Penalty(
    @SerializedName("commited")
    val commitedPenalty: Int,
    @SerializedName("missed")
    val missedPenalty: Int,
    @SerializedName("saved")
    val savedPenalty: Int,
    @SerializedName("success")
    val successPenalty: Int,
    @SerializedName("won")
    val wonPenalty: Int
)