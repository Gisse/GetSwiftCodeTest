package com.djevtic.myswifttestcode.data

import android.util.Log
import com.djevtic.myswifttestcode.App
import com.djevtic.myswifttestcode.extensions.ioToMain
import com.djevtic.myswifttestcode.network.ApiService
import com.djevtic.myswifttestcode.network.ApiSwiftInterface
import com.djevtic.myswifttestcode.network.SwiftUsecaseImpl
import com.djevtic.myswifttestcode.network.models.standing.Standing
import com.djevtic.myswifttestcode.utils.TimeUtils
import io.reactivex.Single

object DataManager {

    private val swiftUsecase =
        SwiftUsecaseImpl(ApiService.getSwiftClient().create(ApiSwiftInterface::class.java))

    fun getStandingsData(leagueId : Int): Single<List<Standing>> {
        var database = App.getDatabase()
        val lastUpdateTime = database.standingDao().getLastUpdateTimestamp(leagueId)
        if(TimeUtils.didDayPassed(lastUpdateTime)) {
            return swiftUsecase.getLeagueStanding(leagueId).map {
                var standingList : MutableList<Standing> = ArrayList<Standing>()
                it.body()?.api?.standings?.let { standing ->
                    standingList.addAll(standing[0])
                    database.standingDao().insertAll(standing[0])
                }
                return@map standingList
            }
        } else {
            return database.standingDao().getAll()
        }
    }

}