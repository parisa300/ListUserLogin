package com.base.sampleapp_part.data.repository

import com.base.sampleapp_part.data.local.UserDao
import com.base.sampleapp_part.data.remote.ListRemoteDataSource
import com.base.sampleapp_part.utils.performGetOperation

import javax.inject.Inject

class ListRepository @Inject constructor(
    private val remoteDataSource: ListRemoteDataSource,
    private val localDataSource: UserDao
) {


    fun getUsers() = performGetOperation(
        databaseQuery = { localDataSource.getAllUsers() },
        networkCall = { remoteDataSource.getUsers() },
        saveCallResult = { localDataSource.insertAll(it.data) }
    )
}