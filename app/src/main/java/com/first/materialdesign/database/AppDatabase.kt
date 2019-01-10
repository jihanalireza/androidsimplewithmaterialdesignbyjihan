package com.first.materialdesign.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(TransaksiHutang::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transaksiHutangDao(): TransaksiHutangDao

    companion object {
        fun getDatabase(context: Context): AppDatabase {
            val db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "newdblocal"
            ).build()

            return db
        }
    }
}