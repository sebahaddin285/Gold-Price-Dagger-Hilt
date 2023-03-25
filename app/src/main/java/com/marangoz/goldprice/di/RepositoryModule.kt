package com.marangoz.goldprice.di

import com.marangoz.goldprice.api.RetrofitService
import com.marangoz.goldprice.repository.Repository
import com.marangoz.goldprice.room.GoldDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideRepotitory(api: RetrofitService, goldDao: GoldDao): Repository {
        return Repository(api, goldDao)
    }


}