package com.example.daggerandroidcomponentskotlin.dagger

import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.daggerandroidcomponentskotlin.api.UserWebService
import com.example.daggerandroidcomponentskotlin.db.MyDatabase
import com.example.daggerandroidcomponentskotlin.db.UserDao
import com.example.daggerandroidcomponentskotlin.ui.UserRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): MyDatabase {
        return Room.databaseBuilder(application, MyDatabase::class.java, "user.db")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    /*val request =
                      OneTimeWorkRequest.Builder(AppWorker::class.java) //.Builder(AppWorker::class.java) //<AppWorker>()
                           .build()
                   request.id
                   val result = WorkManager.getInstance()?.enqueue(request)*/
                }
            }).build()
    }
//
//    @Provides
//    @Singleton
//    internal fun provideUserDao(myDatabase: MyDatabase):UserDao {
//
//        return myDatabase.userDao()
//    }

    @Provides
    @Singleton
    internal fun provideTickerDao(database: MyDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    internal fun provideExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

    @Provides
    @Singleton
    internal fun provideTickerRepository(
        webservice: UserWebService,
        userDao: UserDao,
        executor: Executor
    ): UserRepository {
        return UserRepository(webservice, userDao, executor)
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(gson)
        ).baseUrl(BASE_URL).build()
    }

    @Provides
    @Singleton
    internal fun provideApiWebservice(restAdapter: Retrofit): UserWebService {
        return restAdapter.create<UserWebService>(UserWebService::class.java)
    }

    companion object {
        private val BASE_URL = "https://reqres.in/"
    }
}
