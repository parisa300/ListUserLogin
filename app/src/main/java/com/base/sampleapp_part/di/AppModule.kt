package com.base.sampleapp_part.di

import android.content.Context
import com.base.sampleapp_part.data.local.AppDatabase
import com.base.sampleapp_part.data.local.UserDao

import com.base.sampleapp_part.data.remote.ListRemoteDataSource
import com.base.sampleapp_part.data.remote.ListService
import com.base.sampleapp_part.data.repository.ListRepository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://reqres.in/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideListService(retrofit: Retrofit): ListService = retrofit.create(ListService::class.java)

    @Singleton
    @Provides
    fun provideListRemoteDataSource(listService: ListService) = ListRemoteDataSource(listService)

  @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: ListRemoteDataSource,
                          localDataSource: UserDao
    ) =
        ListRepository(remoteDataSource, localDataSource)
}
