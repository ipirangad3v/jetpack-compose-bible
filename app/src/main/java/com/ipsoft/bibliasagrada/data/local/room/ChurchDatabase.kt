package com.ipsoft.bibliasagrada.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ipsoft.bibliasagrada.data.local.room.ChurchDatabase.Companion.BIBLE_DB_VERSION
import com.ipsoft.bibliasagrada.domain.model.Abbrev
import com.ipsoft.bibliasagrada.domain.model.AbbrevRoomModel
import com.ipsoft.bibliasagrada.domain.model.Book
import com.ipsoft.bibliasagrada.domain.model.BookResponse
import com.ipsoft.bibliasagrada.domain.model.Chapter
import com.ipsoft.bibliasagrada.domain.model.ChapterResponse
import com.ipsoft.bibliasagrada.domain.model.Verse
import javax.inject.Singleton

@Singleton
@Database(
    entities =
    [
        BookResponse::class,
        Abbrev::class,
        Verse::class,
        Chapter::class,
        ChapterResponse::class,
        Book::class,
        AbbrevRoomModel::class
    ],
    version = BIBLE_DB_VERSION,
    exportSchema = false
)
@TypeConverters(ChurchConverters::class)
abstract class ChurchDatabase : RoomDatabase() {
    abstract fun churchDao(): ChurchDao

    companion object {
        const val BIBLE_DB_VERSION = 18
        const val BIBLE_DB_NAME = "bible"
        const val BOOKS_TABLE = "books"
        const val CHAPTERS_TABLE = "chapters"
        const val ABBREVS_TABLE = "abbrevsRoom"
    }
}
