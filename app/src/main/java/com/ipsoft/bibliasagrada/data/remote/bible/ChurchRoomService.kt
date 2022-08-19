package com.ipsoft.bibliasagrada.data.remote.bible

import com.ipsoft.bibliasagrada.domain.model.BookResponse
import com.ipsoft.bibliasagrada.domain.model.ChapterResponse
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChurchRoomService
@Inject constructor(retrofit: Retrofit) : ChurchRoomApi {
    private val api by lazy { retrofit.create(ChurchRoomApi::class.java) }

    override fun getBooks(): Call<List<BookResponse>> = api.getBooks()
    override fun getChapter(url: String): Call<ChapterResponse> =
        api.getChapter(url)
}
