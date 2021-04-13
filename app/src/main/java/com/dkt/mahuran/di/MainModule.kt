package com.dkt.mahuran.di

import com.dkt.mahuran.data.local.MaruhanDatabase
import com.dkt.mahuran.data.local.dao.HallDao
import com.dkt.mahuran.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class MainModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideHallDao(maruhanDatabase: MaruhanDatabase): HallDao{
        return maruhanDatabase.hallDao()
    }
}