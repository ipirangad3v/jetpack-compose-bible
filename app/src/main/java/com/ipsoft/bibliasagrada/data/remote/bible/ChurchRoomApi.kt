package com.ipsoft.bibliasagrada.data.remote.bible

import com.ipsoft.bibliasagrada.domain.model.BookResponse
import com.ipsoft.bibliasagrada.domain.model.ChapterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ChurchRoomApi {

    @GET("books")
    fun getBooks(): Call<List<BookResponse>>

    @GET
    fun getChapter(@Url url: String): Call<ChapterResponse>
}
