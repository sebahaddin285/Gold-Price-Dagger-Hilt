package com.marangoz.goldprice.di

import android.content.Context
import androidx.room.Room
import com.marangoz.goldprice.api.RetrofitService
import com.marangoz.goldprice.repository.Repository
import com.marangoz.goldprice.room.GoldDao
import com.marangoz.goldprice.room.GoldDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://api.collectapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }


    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): GoldDataBase {
        return Room.databaseBuilder(context, GoldDataBase::class.java,
            GoldDataBase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideGoldDAO(goldDataBase: GoldDataBase): GoldDao {
        return goldDataBase.goldDao()
    }


}