package com.example.daggerandroidcomponentskotlin.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Data::class), version = 3, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    @Volatile
    private var INSTANCE: MyDatabase? = null
    abstract fun userDao(): UserDao
}