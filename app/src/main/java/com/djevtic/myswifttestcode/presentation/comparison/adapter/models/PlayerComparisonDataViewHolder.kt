package com.djevtic.myswifttestcode.presentation.comparison.adapter.models

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.djevtic.myswifttestcode.R

class PlayerComparisonDataViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.player_comparison_data_view_holder, parent, false)) {

    private var playerDataLabel: TextView? = null
    private var player1Value: TextView? = null
    private var player2Value: TextView? = null

    init {
        playerDataLabel = itemView.findViewById(R.id.playerDataLabel)
        player1Value = itemView.findViewById(R.id.player1Value)
        player2Value = itemView.findViewById(R.id.player2Value)
    }

    fun bind(
        playerData: PlayerData
    ) {
        if(playerData != null) {
            playerDataLabel?.text = playerData.title
            player1Value?.text = playerData.player1Data
            player2Value?.text = playerData.player2Data
        }
    }
}