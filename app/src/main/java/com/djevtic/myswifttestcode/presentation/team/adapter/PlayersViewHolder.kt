package com.djevtic.myswifttestcode.presentation.team.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.djevtic.myswifttestcode.R
import com.djevtic.myswifttestcode.network.models.playersinteam.Player

class PlayersViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.player_view_holder_layout, parent, false)) {

    private var constLayout: ConstraintLayout? = null
    private var playerName: TextView? = null
    private var playerPosition: TextView? = null
    private var playerPlayed: TextView? = null

    init {
        constLayout = itemView.findViewById(R.id.constLayout)
        playerName = itemView.findViewById(R.id.playerName)
        playerPosition = itemView.findViewById(R.id.playerPosition)
        playerPlayed = itemView.findViewById(R.id.playerPlayed)
    }

    fun bind(
        playerData: Player?,
        callback: TeamPlayersAdapter.OnPlayerItemClickListener
    ) {
        if(playerData != null) {
            if (playerData.selected) {
                constLayout?.setBackgroundColor(Color.DKGRAY)
            } else {
                constLayout?.setBackgroundColor(Color.WHITE)
            }
            constLayout?.setOnClickListener {
                if (playerData != null) {
                    callback.playerClicked(playerData)
                }
            }
            constLayout?.setOnLongClickListener {
                if (playerData != null) {
                    callback.playerLongClicked(playerData)
                }
                true
            }
            playerName?.text = "${playerData.firstname} ${playerData.lastname}"
            playerPosition?.text = playerData.position
            playerPlayed?.text = playerData.games.appearencesGames.toString()
        }
    }
}