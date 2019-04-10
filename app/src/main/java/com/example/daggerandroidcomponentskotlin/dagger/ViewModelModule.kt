package com.example.daggerandroidcomponentskotlin.dagger

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.daggerandroidcomponentskotlin.ui.UserViewModel
import com.example.daggerandroidcomponentskotlin.ui.UserViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    internal abstract fun bindTickerViewModel(repoViewModel: UserViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: UserViewModelFactory): ViewModelProvider.Factory
}