package com.base.sampleapp_part.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.base.sampleapp_part.data.entities.user


@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAllUsers() : LiveData<List<user>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<user>)




}
