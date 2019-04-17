package com.example.daggerandroidcomponentskotlin.works

import androidx.work.Worker
import androidx.work.Data


class LoadAllDataWorker : Worker() {

    override fun doWork(): Result {
        //here do http or database requests
        val result = "islem tamamlandi"

        if (result != null) {
            outputData = Data.Builder()
                .putString(MY_KEY_DATA_FROM_WORKER, result)
                .build()

            return Result.SUCCESS
        }

        return Result.FAILURE
    }

    companion object {

        val MY_KEY_DATA_FROM_WORKER = "MY_KEY_DATA_FROM_WORKER"
    }
}