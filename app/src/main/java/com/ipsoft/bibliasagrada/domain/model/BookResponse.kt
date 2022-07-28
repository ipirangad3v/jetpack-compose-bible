package com.ipsoft.bibliasagrada.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.ipsoft.bibliasagrada.data.local.room.ChurchDatabase.Companion.BOOKS_TABLE

@Entity(tableName = BOOKS_TABLE)
data class BookResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("abbrev")
    var abbrev: Abbrev = Abbrev(),
    @SerializedName("author")
    val author: String = "",
    @SerializedName("chapters")
    val chapters: Int = 0,
    @SerializedName("group")
    val group: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("testament")
    val testament: String = ""
)
