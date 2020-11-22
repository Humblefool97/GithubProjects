package com.example.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Config(
    @PrimaryKey(autoGenerate = true)
    var id: Int = -1,
    var lastCachedTime: Long
)
