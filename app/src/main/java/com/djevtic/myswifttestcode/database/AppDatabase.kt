package com.djevtic.myswifttestcode.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.djevtic.myswifttestcode.database.dao.PlayerDao
import com.djevtic.myswifttestcode.database.dao.StandingDao
import com.djevtic.myswifttestcode.network.models.playersinteam.Player
import com.djevtic.myswifttestcode.network.models.standing.Standing

@Database(entities = [Standing::class, Player::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun standingDao(): StandingDao
    abstract fun playerDao(): PlayerDao
}