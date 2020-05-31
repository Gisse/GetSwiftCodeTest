package com.djevtic.myswifttestcode

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.djevtic.myswifttestcode.data.DataManager
import com.djevtic.myswifttestcode.extensions.ioToMain
import com.djevtic.myswifttestcode.network.models.standing.Standing
import com.djevtic.myswifttestcode.presentation.league.adapter.StandingPackageAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), StandingPackageAdapter.OnStandingItemClickListener {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var spinnerAdapter: ArrayAdapter<CharSequence>

    private var standingList: List<Standing> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                    (viewAdapter as StandingPackageAdapter).setDataSet(standingList)
                    viewAdapter.notifyDataSetChanged()
                }
                filter.toString() == resources.getString(R.string.filter_lost) -> {
                    standingList = standingList.sortedWith(compareBy { it.all.loseAll })
                    (viewAdapter as StandingPackageAdapter).setDataSet(standingList)
                    viewAdapter.notifyDataSetChanged()
                }
                filter.toString() == resources.getString(R.string.filter_won) -> {
                    standingList = standingList.sortedWith(compareBy { it.all.winAll })
                    (viewAdapter as StandingPackageAdapter).setDataSet(standingList)
                    viewAdapter.notifyDataSetChanged()
                }
                filter.toString() == resources.getString(R.string.filter_points) -> {
                    standingList = standingList.sortedWith(compareBy { it.points })
                    (viewAdapter as StandingPackageAdapter).setDataSet(standingList)
                    viewAdapter.notifyDataSetChanged()
                }
                filter.toString() == resources.getString(R.string.filter_position) -> {
                    standingList = standingList.sortedWith(compareBy { it.rank })
                    (viewAdapter as StandingPackageAdapter).setDataSet(standingList)
                    viewAdapter.notifyDataSetChanged()
                }
                filter.toString() == resources.getString(R.string.filter_played) -> {
                    standingList =
                        standingList.sortedWith(compareBy { it.all.matchsPlayedAll })
                    (viewAdapter as StandingPackageAdapter).setDataSet(standingList)
                    viewAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun prepareViewAndData() {
        viewAdapter = StandingPackageAdapter(this)
        (viewAdapter as StandingPackageAdapter).setDataSet(standingList)
        leagueRecycleView.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            viewManager = LinearLayoutManager(context)
            layoutManager = viewManager
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("djevtic", "")
        getData()
    }

    private fun getData() {
        disposable.add(DataManager.getStandingsData(524).ioToMain().subscribe(
            {
                standingList = it
                prepareViewAndData()
            }
            ,
            {
                Log.d("djevtic", "Trowable $it")
//                    DataManager.getDataFromNetworkOrDatabase(524, 0)
            }
        ))
    }

    override fun teamClicked(item: Standing) {
        Log.d("djevtic", "We have selected team ${item.teamName}")
    }
}
