package com.ipsoft.bibliasagrada.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Chapter(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("number")
    val number: Int = 0,
    @SerializedName("verses")
    val verses: Int = 0
)
