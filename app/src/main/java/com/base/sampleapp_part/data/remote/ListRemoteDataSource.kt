package com.base.sampleapp_part.data.remote

import javax.inject.Inject

class ListRemoteDataSource @Inject constructor(
    private val listService: ListService
): BaseDataSource() {

    suspend fun getUsers() = getResult { listService.getAllLists() }

}