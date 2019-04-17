package com.example.daggerandroidcomponentskotlin.ui

import android.arch.lifecycle.*
import android.util.Log
import androidx.work.*
import com.example.daggerandroidcomponentskotlin.works.LoadAllDataWorker


class SplashViewModel : ViewModel() {

    private val myLiveData = MutableLiveData<String>()

    fun loadDataFromWorker(lifecycleOwner: LifecycleOwner) {


        var a = LoadAllDataWorker()
        var requests = ArrayList<Worker>()
        requests.add(a)
        requests.add(a)

        val myWorkerReq = OneTimeWorkRequest.Builder(requests[0].javaClass).addTag("loadDataFromWorker")
            .build()
        //val myWorkerReq2 = OneTimeWorkRequest.Builder(requests[0].javaClass).addTag("loadDataFromWorker")
          //  .build()


        //val mWorkManager = WorkManager.getInstance()
        WorkManager.getInstance()!!
            .beginWith(myWorkerReq)
            .enqueue()

        var workerList = WorkManager.getInstance()?.getStatusesByTag("loadDataFromWorker")
        var workerLiveData = workerList?.value
        for (worker in workerLiveData!!){

            Log.i("Test",""+worker.state)
        }


        WorkManager.getInstance()?.getStatusById(myWorkerReq.id)?.observe(lifecycleOwner, Observer<WorkStatus> {

            if (it?.state?.isFinished!!) {
                var workOutputData: Data = it.outputData
                myLiveData.value = workOutputData.getString(LoadAllDataWorker.MY_KEY_DATA_FROM_WORKER, null)
            }
        })
    }

    fun getData(): MutableLiveData<String> {
        return myLiveData
    }
}