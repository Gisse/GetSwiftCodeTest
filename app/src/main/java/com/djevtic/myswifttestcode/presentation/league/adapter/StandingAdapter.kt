package com.djevtic.myswifttestcode.presentation.league.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.djevtic.myswifttestcode.network.models.standing.Standing

class StandingAdapter(private val onItemClickListener: OnStandingItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setDataSet(myDataset: List<Standing>?) {
        this.myDataset = myDataset
    }

    private var myDataset: List<Standing>? = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StandingViewHolder(
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
            (holder as StandingViewHolder).bind(null, onItemClickListener)
        } else {
            (holder as StandingViewHolder).bind(myDataset!![position], onItemClickListener)
        }
    }

    interface OnStandingItemClickListener {
        fun teamClicked(item: Standing)
    }
}