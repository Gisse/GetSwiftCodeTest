package com.djevtic.myswifttestcode.database.dao

import androidx.room.*
import com.djevtic.myswifttestcode.network.models.standing.Standing
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface StandingDao {
    @Query("SELECT * FROM standing ORDER BY rank")
    fun getAll(): Single<List<Standing>>

    @Query("SELECT * FROM standing WHERE teamId IN (:teamIds)")
    fun loadAllByIds(teamIds: IntArray): Single<List<Standing>>

    @Query("SELECT dataUpdate FROM standing WHERE leagueId IN (:leagueId) LIMIT 1")
    fun getLastUpdateTimestamp(leagueId: Int): Single<Long>

    @Query("SELECT * FROM standing WHERE teamName LIKE :teamName LIMIT 1")
    fun findByName(teamName: String): Single<Standing>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(standing: List<Standing>):Completable

    @Delete
    fun delete(standing: Standing):Completable
}