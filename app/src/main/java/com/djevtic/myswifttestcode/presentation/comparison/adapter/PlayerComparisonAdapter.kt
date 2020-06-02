package com.djevtic.myswifttestcode.presentation.comparison.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.djevtic.myswifttestcode.presentation.comparison.adapter.models.PlayerComparisonDataViewHolder
import com.djevtic.myswifttestcode.presentation.comparison.adapter.models.PlayerData

class PlayerComparisonAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val WIN = 0
        const val DATA = 1
    }

    fun setDataSet(myDataset: List<PlayerData>) {
        this.myDataset = myDataset
    }

    private var myDataset: List<PlayerData> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            WIN -> {
                PlayersSemaforeViewHolder(
                    LayoutInflater.from(parent.context),
                    parent
                )
            }
            DATA -> {
                PlayerComparisonDataViewHolder(
                    LayoutInflater.from(parent.context),
                    parent
                )
            }
            else -> PlayerComparisonDataViewHolder(
                LayoutInflater.from(parent.context),
                parent
            )
        }
    }

    override fun getItemCount(): Int {
        if (myDataset.isNullOrEmpty()) {
            return 0
        }
        return myDataset.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (myDataset.isNullOrEmpty()) {
            (holder as PlayersSemaforeViewHolder).bind(null)
        } else {
            if (holder is PlayersSemaforeViewHolder) {
                holder.bind(myDataset[position])
            } else if (holder is PlayerComparisonDataViewHolder) {
                holder.bind(myDataset[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return this.myDataset[position].itemType
    }
}