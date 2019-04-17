package com.example.daggerandroidcomponentskotlin.dagger;

import com.example.daggerandroidcomponentskotlin.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBinderModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

    //@ContributesAndroidInjector
    //internal abstract fun contributeİkinciSayfa(): İkinciSayfa
}
