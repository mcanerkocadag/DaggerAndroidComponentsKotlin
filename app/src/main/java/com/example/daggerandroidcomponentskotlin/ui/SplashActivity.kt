package com.example.daggerandroidcomponentskotlin.ui

import android.support.v7.app.AppCompatActivity
import android.arch.lifecycle.ViewModelProviders
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import com.example.daggerandroidcomponentskotlin.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //declare model and start loading data in Worker
        val model = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        model.loadDataFromWorker(this)

        //observe data from model
        model.getData().observe(this, object : Observer<String> {
            override fun onChanged(t: String?) {
                Log.i(SplashActivity::class.simpleName, "WorkManager result: " + t)
            }

        })
    }
}