package com.ipsoft.bibliasagrada.domain.core.plataform

import com.ipsoft.bibliasagrada.BuildConfig
import com.ipsoft.bibliasagrada.domain.common.constants.PLATFORM
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {

        proceed(
            request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("platform", PLATFORM)
                .addHeader("app_version", BuildConfig.VERSION_NAME)
                .addHeader("Access-Control-Allow-Origin", "*")
                .addHeader("Connection", "Keep-Alive")
                .addHeader("Accept", "application/json")
                .build()
        )
    }
}
