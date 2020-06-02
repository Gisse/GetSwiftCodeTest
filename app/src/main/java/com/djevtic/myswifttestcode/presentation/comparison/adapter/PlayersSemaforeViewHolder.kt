package com.djevtic.myswifttestcode.presentation.comparison.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.djevtic.myswifttestcode.R
import com.djevtic.myswifttestcode.presentation.comparison.adapter.models.PlayerData

class PlayersSemaforeViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.player_comparison_semafore_view_holder,
            parent,
            false
        )
    ) {

    private var player1marker: View? = null
    private var player2marker: View? = null
    private var player1text: TextView? = null
    private var player2text: TextView? = null

    init {
        player1marker = itemView.findViewById(R.id.player1marker)
        player2marker = itemView.findViewById(R.id.player2marker)
        player1text = itemView.findViewById(R.id.player1text)
        player2text = itemView.findViewById(R.id.player2text)
    }

    fun bind(
        playerData: PlayerData?
    ) {
        if (playerData != null) {
            if (playerData.playerOne) {
                player1marker?.setBackgroundColor(Color.GREEN)
                player2marker?.setBackgroundColor(Color.RED)
                player1text?.text = player1text?.context?.resources?.getString(R.string.won)
                player2text?.text = player2text?.context?.resources?.getString(R.string.lost)
            }
            if (playerData.playerTwo) {
                player2marker?.setBackgroundColor(Color.GREEN)
                player1marker?.setBackgroundColor(Color.RED)
                player1text?.text = player1text?.context?.resources?.getString(R.string.lost)
                player2text?.text = player2text?.context?.resources?.getString(R.string.won)
            }
        }
    }
}