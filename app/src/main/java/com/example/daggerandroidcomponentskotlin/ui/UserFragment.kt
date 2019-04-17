package com.example.daggerandroidcomponentskotlin.ui;

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.daggerandroidcomponentskotlin.R
import com.example.daggerandroidcomponentskotlin.db.Data
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


public class UserFragment : Fragment() {
    private var viewModel: UserViewModel? = null
    private var textView: TextView? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_user_profile, container, false)
        textView = view.findViewById(R.id.textView)
        textView!!.text = "Loading..."
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.configureDagger()
        this.configureViewModel()
    }

    private fun configureDagger() {

        AndroidSupportInjection.inject(this)
    }

    private fun configureViewModel() {
        val symbol = arguments!!.getString(UID_KEY)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)
        viewModel!!.init(symbol)
        viewModel!!.ticker?.observe(this, object : Observer<List<Data>> {
            override fun onChanged(data: List<Data>?) {

                updateUI(data)
            }
        })
    }

    private fun updateUI(dataList: List<Data>?) {

        if (dataList == null)
            return

        val builder = StringBuilder()
        for (data in dataList) {
            if (data == null)
                continue

            builder.append("İsim: ").append(data.first_name).append("\n\n")
        }

        textView!!.text = builder.toString()
    }

    companion object {

        const val UID_KEY = "uid"
    }
}

