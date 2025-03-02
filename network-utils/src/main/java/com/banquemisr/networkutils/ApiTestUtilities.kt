package com.banquemisr.networkutils

import com.banquemisr.retrofitfactory.RetrofitFactory
import okhttp3.*
import retrofit2.create

inline fun <reified T> createService(interceptor: Interceptor, url: String): T {
    return RetrofitFactory.createRetrofit(
        okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build(),
        baseUrl = url
    ).create()
}
