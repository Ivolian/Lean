package com.ivotai.lean.app.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.ivotai.lean.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @AppScope
    @Provides
    fun okHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG)
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .request("Request")
                    .response("Response")
                    .addHeader("version", BuildConfig.VERSION_NAME)
                    .build()
            )
            .build()

    companion object {
        //        val baseUrl = "http://ivotai.nat300.top/lean/"
//        val baseUrl = "http://192.168.1.5:8080/"
        val baseUrl = "http://192.168.1.5:8090/lean/"

    }

    @AppScope
    @Provides
    fun gson(): Gson = GsonBuilder().create()

    @AppScope
    @Provides
    fun retrofit(client: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

}