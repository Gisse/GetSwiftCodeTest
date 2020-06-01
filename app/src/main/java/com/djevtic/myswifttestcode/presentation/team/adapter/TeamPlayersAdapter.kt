package com.djevtic.myswifttestcode.presentation.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.djevtic.myswifttestcode.network.models.playersinteam.Player

class TeamPlayersAdapter (val onItemClickListener: OnPlayerItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setDataSet(myDataset: List<Player>?) {
        this.myDataset = myDataset
    }

    private var myDataset: List<Player>? = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlayersViewHolder(
            LayoutInflater.from(parent.context),
            parent
        )
    }

    override fun getItemCount(): Int {
        if (myDataset.isNullOrEmpty()) {
            return 0
        }
        return myDataset?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (myDataset.isNullOrEmpty()) {
            (holder as PlayersViewHolder).bind(null, onItemClickListener)
        } else {
            (holder as PlayersViewHolder).bind(myDataset!![position], onItemClickListener)
        }
    }

    interface OnPlayerItemClickListener {
        fun playerClicked(item: Player)
        fun playerLongClicked(item: Player)
    }

}