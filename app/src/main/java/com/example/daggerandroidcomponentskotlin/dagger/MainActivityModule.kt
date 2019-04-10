package com.example.daggerandroidcomponentskotlin.dagger;

import com.example.daggerandroidcomponentskotlin.ui.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeTickerFragment(): UserFragment
}
