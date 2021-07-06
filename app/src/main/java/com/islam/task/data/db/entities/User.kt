package com.islam.task.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0

@Entity(tableName = "user")
data class User(
    val idp: String, // local

) {
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}