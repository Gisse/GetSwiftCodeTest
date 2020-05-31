package com.djevtic.myswifttestcode.data

import com.djevtic.myswifttestcode.App
import com.djevtic.myswifttestcode.extensions.subscribeAsync
import com.djevtic.myswifttestcode.network.ApiService
import com.djevtic.myswifttestcode.network.ApiSwiftInterface
import com.djevtic.myswifttestcode.network.SwiftUsecaseImpl
import com.djevtic.myswifttestcode.network.models.standing.Standing
import com.djevtic.myswifttestcode.utils.TimeUtils
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers


object DataManager {

    private val swiftUsecase =
        SwiftUsecaseImpl(ApiService.getSwiftClient().create(ApiSwiftInterface::class.java))

//    fun getStandingsData(leagueId: Int): Single<List<Standing>> {
//        var database = App.getDatabase()
//        var lastUpdateTime = 0L
//        database.standingDao().getLastUpdateTimestamp(leagueId).flatMap {
//            if (TimeUtils.didDayPassed(lastUpdateTime)) {
//                return swiftUsecase.getLeagueStanding(it).map {
//                    var standingList: MutableList<Standing> = ArrayList<Standing>()
//                    it.body()?.api?.standings?.let { standing ->
//                        standingList.addAll(standing[0])
//                        database.standingDao().insertAll(standing[0])
//                    }
//                    return@map standingList
//                }
//            } else {
//                return database.standingDao().getAll()
//            }
//        }
//    }

    fun getDataFromNetworkOrDatabase(leagueId: Int, lastUpdateTime: Long): Single<List<Standing>> {
        var database = App.getDatabase()
        if (TimeUtils.didDayPassed(lastUpdateTime)) {
            return swiftUsecase.getLeagueStanding(leagueId)
                .map {
                    var standingList: List<Standing> = arrayListOf()
                    it.body()?.api?.standings?.let { standing ->
                        standingList = standing[0]
                    }
                    return@map standingList
                }.doOnSuccess {
                    database.standingDao().insertAll(it)
                }
        } else {
            return database.standingDao().getAll()
        }
    }

    fun getStandingsData(leagueId: Int): Single<List<Standing>> {
        var database = App.getDatabase()
        return database.standingDao().getLastUpdateTimestamp(leagueId)
            .flatMap {
                getDataFromNetworkOrDatabase(leagueId, it)
            }.onErrorResumeNext {
                getDataFromNetworkOrDatabase(leagueId, 0)
            }
    }
}