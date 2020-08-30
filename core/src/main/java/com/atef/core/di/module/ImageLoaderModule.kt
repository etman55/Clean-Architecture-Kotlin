package com.atef.core.di.module

import com.atef.core.imageloader.ImageLoader
import com.atef.core.imageloader.ImageLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
interface ImageLoaderModule {

    @get:Binds
    val ImageLoaderImpl.imageLoader: ImageLoader
}
