package com.example.daggerandroidcomponentskotlin.dagger;

import android.app.Application
import com.example.daggerandroidcomponentskotlin.App
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ActivityBinderModule::class])
public interface AppComponent {

    @Component.Builder
    public interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    // this method is called by the application class (App.kt)
    public fun inject(app: App)
}
