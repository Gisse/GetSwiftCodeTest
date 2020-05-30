package com.djevtic.myswifttestcode.database.dao

import androidx.room.*
import com.djevtic.myswifttestcode.network.models.leagueteams.Team
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TeamDao {
    @Query("SELECT * FROM team")
    fun getAll(): Single<List<Team>>

    @Query("SELECT * FROM team WHERE teamId IN (:teamIds)")
    fun loadAllByIds(teamIds: IntArray): Single<List<Team>>

    @Query(
        "SELECT * FROM team WHERE name LIKE :teamName LIMIT 1"
    )
    fun findByName(teamName: String): Single<Team>

    @Query(
        "SELECT * FROM team WHERE teamId LIKE :teamId LIMIT 1"
    )
    fun findById(teamId: Int): Single<Team>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(team: List<Team>):Completable

    @Delete
    fun delete(team: Team):Completable
}