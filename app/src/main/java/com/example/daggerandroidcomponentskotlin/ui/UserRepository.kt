package com.example.daggerandroidcomponentskotlin.ui;

import android.arch.lifecycle.LiveData
import android.util.Log
import com.example.daggerandroidcomponentskotlin.api.UserWebService
import com.example.daggerandroidcomponentskotlin.db.Data
import com.example.daggerandroidcomponentskotlin.db.MyPojo
import com.example.daggerandroidcomponentskotlin.db.UserDao
import com.example.daggerandroidcomponentskotlin.db.UserDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject
constructor(private val webservice: UserWebService, private val userDao: UserDao, private val executor: Executor) {

    // try to refresh data if possible from Bitfinex Api
    // return a LiveData directly from the database.
    val users: LiveData<List<Data>>
        get() {
            refreshUsers()
            return userDao.loadAllUser()
        }

    fun getTicker(symbol: String): LiveData<Data> {
        refreshTicker(symbol) // try to refresh data if possible from Bitfinex Api
        return userDao.load(Integer.valueOf(symbol)) // return a LiveData directly from the database.
    }

    private fun refreshTicker(symbol: String) {
        executor.execute {
            // Check if ticker was fetched recently
            val tickerExists = userDao.hasTicker(Integer.valueOf(symbol))
            // If ticker have to be updated
            if (tickerExists == null) {
                webservice.getUser(Integer.valueOf(symbol)).enqueue(object : Callback<UserDetail> {
                    override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                        executor.execute {
                            val userDetail = response.body()
                            //userDetail.setTimestamp((new Date()).getTime());
                            //userDetail.setSymbol(symbol);
                            userDao.save(userDetail!!.data!!)
                        }
                    }

                    override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                        Log.e("_MK", t.message, t)
                    }
                })
            }
        }
    }

    fun refreshUsers() {

        executor.execute {
            webservice.getUsers().enqueue(object : Callback<MyPojo> {
                override fun onResponse(call: Call<MyPojo>, response: Response<MyPojo>) {
                    executor.execute {
                        val userDetail = response.body()
                        //userDetail.setTimestamp((new Date()).getTime());
                        //userDetail.setSymbol(symbol);
                        for (d in userDetail!!.data!!) {

                            if (d == null)
                                continue

                            userDao.save(d)
                        }
                    }
                }

                override fun onFailure(call: Call<MyPojo>, t: Throwable) {
                    Log.e("_MK", t.message, t)
                }
            })
        }
    }

}
