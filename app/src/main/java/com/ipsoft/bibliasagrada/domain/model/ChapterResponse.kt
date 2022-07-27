package com.ipsoft.bibliasagrada.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.ipsoft.bibliasagrada.data.local.room.ChurchDatabase.Companion.CHAPTERS_TABLE
import javax.inject.Singleton

@Singleton
@Entity(tableName = CHAPTERS_TABLE)
data class ChapterResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("book")
    val book: Book = Book(),
    @SerializedName("chapter")
    val chapter: Chapter = Chapter(),
    @SerializedName("verses")
    val verses: List<Verse> = emptyList()
)
