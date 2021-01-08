package com.base.sampleapp_part.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "users")
data class user(
    @PrimaryKey
    val id: Int,
    val avatar: String,
    val email: String,
    val first_name: String,
    val last_name: String

)