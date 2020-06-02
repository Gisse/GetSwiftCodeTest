package com.djevtic.myswifttestcode.database.dao

import androidx.room.*
import com.djevtic.myswifttestcode.network.models.playersinteam.Player
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PlayerDao {
    @Query("SELECT * FROM player")
    fun getAll(): Single<List<Player>>

    @Query("SELECT * FROM player WHERE playerId IN (:playerId)")
    fun loadAllByIds(playerId: IntArray): Single<List<Player>>

    @Query("SELECT dataUpdate FROM player WHERE teamId IN (:teamId) LIMIT 1")
    fun getPlayersLastUpdateTimestamp(teamId: Int): Single<Long>

    @Query("SELECT * FROM player WHERE playerId LIKE :playerId LIMIT 1")
    fun findByName(playerId: Int): Single<Player>

    @Query("SELECT * FROM player WHERE teamId LIKE :teamId")
    fun getAllPlayersForTeam(teamId: Int): Single<List<Player>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(player: List<Player>): Completable

    @Delete
    fun delete(player: Player): Completable
}