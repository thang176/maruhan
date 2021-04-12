package com.dkt.mahuran.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder().addInterceptor{chain: Interceptor.Chain ->
            val request = chain.request().newBuilder()
                    .addHeader("android_auth_key", "FF3AFB28041ACBF72B5085726738839DD38B0197")
                    .addHeader("android_package", "jp.co.maruhan.android")
                    .addHeader("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 5.1.1; SM-N976N Build/QP1A.190711.020) - Android")
                    .build()
            chain.proceed(request)
        }.build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
                .baseUrl("https://nifmbapi.maruhan.co.jp/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}