package com.yourcompany.reportmaker.di

import android.content.ContentResolver
import android.content.Context
import com.yourcompany.reportmaker.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides @Singleton
    fun provideAddTextOverlayUseCase() = AddTextOverlayUseCase()

    @Provides @Singleton
    fun provideUpdateOverlayTransformUseCase() = UpdateOverlayTransformUseCase()

    @Provides @Singleton
    fun provideSetOverlaySelectedUseCase() = SetOverlaySelectedUseCase()

    @Provides @Singleton
    fun provideRenderImageWithOverlaysUseCase() = RenderImageWithOverlaysUseCase()

    @Provides @Singleton
    fun provideLoadImagesFromPickerUseCase(
        resolver: ContentResolver
    ) = LoadImagesFromPickerUseCase(resolver)

    @Provides @Singleton
    fun provideConvertCameraImageUseCase() = ConvertCameraImageUseCase()

    @Provides @Singleton
    fun provideCreatePDFUseCase(
        context: Context
    ) = CreatePDFUseCase(context)
}
