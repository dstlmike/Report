package com.yourcompany.reportmaker.di

import android.content.Context
import android.content.ContentResolver
import com.yourcompany.reportmaker.ReportMakerApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppContext(app: ReportMakerApp): Context = app

    @Provides
    @Singleton
    fun provideContentResolver(context: Context): ContentResolver =
        context.contentResolver
}
