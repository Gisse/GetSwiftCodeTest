package com.djevtic.myswifttestcode.presentation.team

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.djevtic.myswifttestcode.BaseActivity
import com.djevtic.myswifttestcode.MainActivity.Companion.PLAYER
import com.djevtic.myswifttestcode.MainActivity.Companion.PLAYER2
import com.djevtic.myswifttestcode.MainActivity.Companion.TEAM
import com.djevtic.myswifttestcode.R
import com.djevtic.myswifttestcode.data.DataManager
import com.djevtic.myswifttestcode.extensions.gone
import com.djevtic.myswifttestcode.extensions.ioToMain
import com.djevtic.myswifttestcode.extensions.visible
import com.djevtic.myswifttestcode.network.models.playersinteam.Player
import com.djevtic.myswifttestcode.network.models.standing.Standing
import com.djevtic.myswifttestcode.presentation.comparison.PlayerComparisonActivity
import com.djevtic.myswifttestcode.presentation.player.PlayerInfoActivity
import com.djevtic.myswifttestcode.presentation.team.adapter.TeamPlayersAdapter
import com.djevtic.myswifttestcode.utils.Serializer
import com.djevtic.myswifttestcode.utils.TypeFactory
import kotlinx.android.synthetic.main.activity_team_layout.*

class TeamActivity : BaseActivity(), TeamPlayersAdapter.OnPlayerItemClickListener {

    companion object {
        const val GOALKEEPER = "Goalkeeper"
        const val DEFENDER = "Defender"
        const val MIDFIELDER = "Midfielder"
        const val ATTACKER = "Attacker"
    }

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var playerList: List<Player> = listOf()
    private var selectedPlayerList : MutableList<Player> = mutableListOf()
    private lateinit var team : Standing

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_layout)
        prepareData()
    }

    override fun onResume() {
        super.onResume()
        selectedPlayerList.clear()
        getData()
    }
    private fun prepareData() {
        val teamData = intent.getStringExtra(TEAM)
        if(teamData != null){
            //Deserialized string to JSON object
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
                        playerList = DataManager.preparePlayerList(it)
                        prepareViewAndData()
                    }
                    ,
                    {
                        DataManager.getPlayersDataFromDatabase(team.teamId).ioToMain().subscribe(
                            {
                                playerList = DataManager.preparePlayerList(it)
                                prepareViewAndData()
                            }
                        ,
                            {
                                progressOverlay.gone()
                            }
                        )
                    }
                ))
        }
    }

    private fun prepareViewAndData() {
        viewAdapter = TeamPlayersAdapter(this)
        (viewAdapter as TeamPlayersAdapter).setDataSet(playerList)
        teamRecycleView.apply {
            setHasFixedSize(true)
            viewManager = LinearLayoutManager(context)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        progressOverlay.gone()
    }

    override fun playerClicked(item: Player) {
        val mBundle = Bundle()
        //Serialized JSON object and send as string
        mBundle.putString(PLAYER, Serializer.serialize(item))
        val intent = Intent(this, PlayerInfoActivity::class.java)
        intent.putExtras(mBundle)
        startActivity(intent)
    }

    override fun playerLongClicked(item: Player) {
        if(item.position != GOALKEEPER){
            if(selectedPlayerList.isEmpty()) {
                selectedPlayerList.add(item)
                var index = playerList.indexOf(item)
                playerList[index].selected = true
                (viewAdapter as TeamPlayersAdapter).setDataSet(playerList)
                viewAdapter.notifyItemChanged(index)
            } else {
                if(selectedPlayerList.contains(item)){
                    var index = playerList.indexOf(item)
                    playerList[index].selected = false
                    (viewAdapter as TeamPlayersAdapter).setDataSet(playerList)
                    viewAdapter.notifyItemChanged(index)
                    selectedPlayerList.remove(item)
                } else {
                    val itemAlreadySelected = selectedPlayerList[0]
                    if(item.position == itemAlreadySelected.position) {
                        val mBundle = Bundle()
                        //Serialized JSON object and send as string
                        mBundle.putString(PLAYER, Serializer.serialize(item))
                        mBundle.putString(PLAYER2, Serializer.serialize(itemAlreadySelected))
                        val intent = Intent(this, PlayerComparisonActivity::class.java)
                        intent.putExtras(mBundle)
                        startActivity(intent)
                    } else {
                        val text = getString(R.string.toast_comparison_position, itemAlreadySelected.position, itemAlreadySelected.position)
                        Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else {
            Toast.makeText(applicationContext, getString(R.string.toast_comparison_goalkeeper), Toast.LENGTH_LONG).show()
        }
    }

}