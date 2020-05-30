package com.djevtic.myswifttestcode.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.djevtic.myswifttestcode.database.dao.StandingDao
import com.djevtic.myswifttestcode.network.models.standing.Standing

@Database(entities = arrayOf(Standing::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun standingDao(): StandingDao
}