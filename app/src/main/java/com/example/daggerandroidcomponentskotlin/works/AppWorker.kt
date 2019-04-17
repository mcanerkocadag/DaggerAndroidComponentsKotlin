package com.example.daggerandroidcomponentskotlin.works;

import android.util.Log
import androidx.work.Worker
import com.example.daggerandroidcomponentskotlin.api.UserWebService
import com.example.daggerandroidcomponentskotlin.db.MyDatabase
import com.example.daggerandroidcomponentskotlin.db.UserDetail
import kotlinx.coroutines.CommonPool.executor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AppWorker
    : Worker() {

    @Inject
    lateinit var myDatabase: MyDatabase

    @Inject
    lateinit var webservice: UserWebService

    override fun doWork(): Result {
        // Do the work here--in this case, upload the images.

        Log.i("WorkManager","doWork")
        //refreshTicker("1")
        //myDatabase.userDao().load(Integer.valueOf("1"))
        Log.i("AppWorker", "AppWorker | doWork()")

        // Indicate whether the task finished successfully with the Result
        return Result.SUCCESS
    }

    private fun refreshTicker(symbol: String) {
        Log.i("WorkManager","refreshTicket basladi")
        executor.execute {

            // Check if ticker was fetched recently
            val tickerExists = myDatabase.userDao().hasTicker(Integer.valueOf(symbol))
            // If ticker have to be updated
            if (tickerExists == null) {
                webservice.getUser(Integer.valueOf(symbol)).enqueue(object : Callback<UserDetail> {
                    override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                        executor.execute {
                            val userDetail = response.body()
                            //userDetail.setTimestamp((new Date()).getTime());
                            //userDetail.setSymbol(symbol);
                            myDatabase.userDao().save(userDetail!!.data!!)
                            Log.i("WorkManager","refreshTicket bitti")
                        }
                    }

                    override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                        Log.e("_MK", t.message, t)
                        Log.i("WorkManager","refreshTicket fail")
                    }
                })
            }
        }
    }
}
