package com.ipsoft.bibliasagrada.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.ipsoft.bibliasagrada.domain.model.Abbrev

@Entity
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("abbrev")
    val abbrev: Abbrev = Abbrev(),
    @SerializedName("author")
    val author: String = "",
    @SerializedName("group")
    val group: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("version")
    val version: String = ""
)
