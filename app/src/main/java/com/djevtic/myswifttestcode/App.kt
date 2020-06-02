package com.djevtic.myswifttestcode

import android.app.Application
import androidx.room.Room
import com.djevtic.myswifttestcode.database.AppDatabase

class App: Application() {

    companion object {
        private lateinit var database : AppDatabase

        fun getDatabase(): AppDatabase {
            return database
        }
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "swift-test").build()
    }

}