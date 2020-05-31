package com.djevtic.myswifttestcode.presentation.league.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.djevtic.myswifttestcode.R
import com.djevtic.myswifttestcode.network.models.standing.Standing

class StandingViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.standing_view_holder_layout, parent, false)) {

    private var constLayout: ConstraintLayout? = null
    private var teamName: TextView? = null
    private var teamPoints: TextView? = null
    private var teamImage: ImageView? = null
    private var teamGoals: TextView? = null
    private var teamPosition: TextView? = null
    private var teamWin: TextView? = null
    private var teamLose: TextView? = null
    private var teamPlayed: TextView? = null

    init {
        constLayout = itemView.findViewById(R.id.constLayout)
        teamName = itemView.findViewById(R.id.teamName)
        teamPoints = itemView.findViewById(R.id.teamPoints)
        teamImage = itemView.findViewById(R.id.teamImage)
        teamGoals = itemView.findViewById(R.id.teamGoals)
        teamPosition = itemView.findViewById(R.id.teamPosition)
        teamWin = itemView.findViewById(R.id.teamWin)
        teamLose = itemView.findViewById(R.id.teamLose)
        teamPlayed = itemView.findViewById(R.id.teamPlayed)
    }

    fun bind(
        standingData: Standing?,
        callback: StandingAdapter.OnStandingItemClickListener
    ) {
        constLayout?.setOnClickListener {
            if (standingData != null) {
                callback.teamClicked(standingData)
            }
        }
        teamName?.text = standingData?.teamName
        teamImage?.let {
            Glide.with(it.context)
                .load(standingData?.logo)
                .override(600, 600)
                .centerCrop()
                .into(it)
        }

        teamPoints?.text = standingData?.points.toString()
        teamGoals?.text = standingData?.all?.goalsAgainstAll.toString()
        teamPosition?.text = standingData?.rank.toString()
        teamWin?.text = standingData?.all?.winAll.toString()
        teamLose?.text = standingData?.all?.loseAll.toString()
        teamPlayed?.text = standingData?.all?.matchsPlayedAll.toString()
    }

}