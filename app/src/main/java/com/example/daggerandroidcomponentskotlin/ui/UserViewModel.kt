package com.example.daggerandroidcomponentskotlin.ui;

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.daggerandroidcomponentskotlin.db.Data
import javax.inject.Inject

class UserViewModel @Inject
constructor(private val tickerRepo: UserRepository) : ViewModel() {

    var ticker: LiveData<List<Data>>? = null
        private set

    fun init(symbol: String) {
        if (this.ticker != null) {
            return
        }
        ticker = tickerRepo.users
    }

}