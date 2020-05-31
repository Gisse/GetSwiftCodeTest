package com.djevtic.myswifttestcode.presentation.team

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.djevtic.myswifttestcode.BaseActivity
import com.djevtic.myswifttestcode.MainActivity.Companion.TEAM
import com.djevtic.myswifttestcode.R
import com.djevtic.myswifttestcode.data.DataManager
import com.djevtic.myswifttestcode.extensions.gone
import com.djevtic.myswifttestcode.extensions.ioToMain
import com.djevtic.myswifttestcode.extensions.visible
import com.djevtic.myswifttestcode.network.models.playersinteam.Player
import com.djevtic.myswifttestcode.network.models.standing.Standing
import com.djevtic.myswifttestcode.presentation.team.adapter.TeamPlayersAdapter
import com.djevtic.myswifttestcode.utils.Serializer
import com.djevtic.myswifttestcode.utils.TypeFactory
import kotlinx.android.synthetic.main.activity_team_layout.*

class TeamActivity : BaseActivity(), TeamPlayersAdapter.OnPlayerItemClickListener {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var playerList: List<Player> = listOf()
    private lateinit var team : Standing

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_layout)
        prepareData()
    }

    override fun onResume() {
        super.onResume()
        getData()
    }
    private fun prepareData() {
        val teamData = intent.getStringExtra(TEAM)
        if(teamData != null){
            team = Serializer.deserialize<Standing>(teamData, TypeFactory.ObjectType.TEAM)
            teamName?.text = team.teamName
            teamImage?.let {
                Glide.with(it.context)
                    .load(team.logo)
                    .override(600, 600)
                    .centerCrop()
                    .into(it)
            }

            teamPoints?.text = team.points.toString()
            teamGoals?.text = team.all.goalsAgainstAll.toString()
            teamPosition?.text = team.rank.toString()
            teamWin?.text = team.all.winAll.toString()
            teamLose?.text = team.all.loseAll.toString()
            teamPlayed?.text = team.all.matchsPlayedAll.toString()
        }
    }

    private fun getData() {
        if (::team.isInitialized) {
            progressOverlay.visible()
            disposable.add(
                DataManager.getPlayersData(team.teamId).ioToMain().subscribe(
                    {
                        playerList = DataManager.prepareList(it)
                        prepareViewAndData()
                    }
                    ,
                    {
                        Log.d("djevtic", "Trowable $it")
                        progressOverlay.gone()
                    }
                ))
        }
    }

    private fun prepareViewAndData() {
        viewAdapter = TeamPlayersAdapter(this)
        (viewAdapter as TeamPlayersAdapter).setDataSet(playerList)
        teamRecycleView.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            viewManager = LinearLayoutManager(context)
            layoutManager = viewManager
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
        progressOverlay.gone()
    }

    override fun playerClicked(item: Player) {
        Log.d("djevtic", "CLicked on player ${item.firstname} ${item.lastname}")
    }

}