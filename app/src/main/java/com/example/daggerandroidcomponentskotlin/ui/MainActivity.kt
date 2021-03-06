package com.example.daggerandroidcomponentskotlin.ui

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.daggerandroidcomponentskotlin.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.showFragment(savedInstanceState)

        //val request = OneTimeWorkRequest.Builder(AppWorker::class.java)
        //    .build()
        //val requestID = request.id

        //WorkManager.getInstance()?.getStatusById(request.id)?.observe(this, Observer {
        //    Log.i("AppWorker", "Work Status: " + it?.state!!.name + "\n")
        //})
        //WorkManager.getInstance()?.enqueue(request)
        //Log.i("WorkManager", "RequestID: $requestID")
        //Log.i("WorkManager", "Enqueue")


//        val request2 = OneTimeWorkRequest.Builder(AppWorker::class.java)
//            .build()
//        val requestID2 = request2.id
//        WorkManager.getInstance()?.enqueue(request2)
//        Log.i("WorkManager", "RequestID 2: $requestID2")

    }

    private fun showFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {

            val fragment = UserFragment()

            val bundle = Bundle()
            bundle.putString(UserFragment.UID_KEY, SYMBOL)
            fragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, null)
                .commit()
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return dispatchingAndroidInjector
    }

    companion object {
        private val SYMBOL = "1"
    }
}
