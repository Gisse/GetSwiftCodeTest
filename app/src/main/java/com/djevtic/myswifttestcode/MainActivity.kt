package com.djevtic.myswifttestcode

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.djevtic.myswifttestcode.data.DataManager
import com.djevtic.myswifttestcode.extensions.gone
import com.djevtic.myswifttestcode.extensions.ioToMain
import com.djevtic.myswifttestcode.extensions.visible
import com.djevtic.myswifttestcode.network.ApiService
import com.djevtic.myswifttestcode.network.ApiSwiftInterface
import com.djevtic.myswifttestcode.network.SwiftUsecaseImpl
import com.djevtic.myswifttestcode.network.models.standing.Standing
import com.djevtic.myswifttestcode.presentation.league.adapter.StandingAdapter
import com.djevtic.myswifttestcode.presentation.team.TeamActivity
import com.djevtic.myswifttestcode.utils.Serializer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), StandingAdapter.OnStandingItemClickListener {

    companion object {
        const val TEAM: String = "team"
        const val PLAYER: String = "player"
        const val PLAYER2: String = "player2"
        const val PREMIER_LEAGUE = 524
    }

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var spinnerAdapter: ArrayAdapter<CharSequence>

    private var standingList: List<Standing> = listOf()

    private val swiftUsecase =
        SwiftUsecaseImpl(ApiService.getSwiftClient().create(ApiSwiftInterface::class.java))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        getData()
        prepareFilter()
    }

    private fun prepareFilter() {
        val spinner: Spinner = findViewById(R.id.spinner_nav)
        ArrayAdapter.createFromResource(
            this,
            R.array.standings_filter_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            spinnerAdapter = adapter
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = spinnerAdapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item = spinnerAdapter.getItem(position)
                performFiltering(item)
            }
        }
    }

    private fun performFiltering(filter: CharSequence?) {
        if (::viewAdapter.isInitialized) {
            when {
                filter.toString() == resources.getString(R.string.filter_name) -> {
                    standingList = standingList.sortedWith(compareBy { it.teamName })
                    (viewAdapter as StandingAdapter).setDataSet(standingList)
                    viewAdapter.notifyDataSetChanged()
                }
                filter.toString() == resources.getString(R.string.filter_lost) -> {
                    standingList = standingList.sortedWith(compareBy { it.all.loseAll })
                    (viewAdapter as StandingAdapter).setDataSet(standingList)
                    viewAdapter.notifyDataSetChanged()
                }
                filter.toString() == resources.getString(R.string.filter_won) -> {
                    standingList = standingList.sortedWith(compareBy { it.all.winAll })
                    (viewAdapter as StandingAdapter).setDataSet(standingList)
                    viewAdapter.notifyDataSetChanged()
                }
                filter.toString() == resources.getString(R.string.filter_points) -> {
                    standingList = standingList.sortedWith(compareBy { it.points })
                    (viewAdapter as StandingAdapter).setDataSet(standingList)
                    viewAdapter.notifyDataSetChanged()
                }
                filter.toString() == resources.getString(R.string.filter_position) -> {
                    standingList = standingList.sortedWith(compareBy { it.rank })
                    (viewAdapter as StandingAdapter).setDataSet(standingList)
                    viewAdapter.notifyDataSetChanged()
                }
                filter.toString() == resources.getString(R.string.filter_played) -> {
                    standingList =
                        standingList.sortedWith(compareBy { it.all.matchsPlayedAll })
                    (viewAdapter as StandingAdapter).setDataSet(standingList)
                    viewAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun prepareViewAndData() {
        viewAdapter = StandingAdapter(this)
        (viewAdapter as StandingAdapter).setDataSet(standingList)
        leagueRecycleView.apply {
            setHasFixedSize(true)
            viewManager = LinearLayoutManager(context)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        checkOverlayGroup.gone()
    }

    private fun getData() {
        checkOverlayGroup.visible()
        disposable.add(DataManager.getStandingsData(PREMIER_LEAGUE).ioToMain().subscribe(
            {
                standingList = it
                prepareViewAndData()
            }
            ,
            {
                //If no network let try to get from database even if data is old
                disposable.add(
                    DataManager.getStandingsFromDatabase(PREMIER_LEAGUE).ioToMain()
                        .subscribe(
                            {
                                standingList = it
                                prepareViewAndData()
                            }
                            ,
                            {
                                checkOverlayGroup.gone()
                            }
                        )
                )
            }
        )
        )
    }

    override fun teamClicked(item: Standing) {
        val mBundle = Bundle()
        //Serialized JSON object and send as string
        mBundle.putString(TEAM, Serializer.serialize(item))
        val intent = Intent(this, TeamActivity::class.java)
        intent.putExtras(mBundle)
        startActivity(intent)
    }
}
