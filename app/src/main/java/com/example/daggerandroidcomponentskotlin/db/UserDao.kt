package com.example.daggerandroidcomponentskotlin.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    abstract fun save(data: Data)


    @Query("SELECT * from data")
    abstract fun loadAllUser(): LiveData<List<Data>>

    @Query("SELECT * from data Where id = :id")
    abstract fun load(id: Int): LiveData<Data>

    @Query("Select * from data Where id=:id LIMIT 1")
    abstract fun hasTicker(id: Int): Data
}