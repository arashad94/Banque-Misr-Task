package com.banquemisr.homecomponent.di

import com.banquemisr.homecomponent.data.api.MoviesByTypeApiService
import com.banquemisr.homecomponent.data.mapper.*
import com.banquemisr.homecomponent.data.mapper.MoviesByTypeMapper
import com.banquemisr.homecomponent.data.repository.DefaultMovieTypesRepository
import com.banquemisr.homecomponent.domain.repository.MovieTypesRepository
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object HomeModule {
    @Singleton
    @Provides
    fun provideMoviesByTypeService(
        retrofit: Retrofit
    ): MoviesByTypeApiService = retrofit.create(MoviesByTypeApiService::class.java)

    @Singleton
    @Provides
    fun provideMoviesByTypeMapper(): MoviesByTypeMapper = DefaultMoviesByTypeMapper()

    @Singleton
    @Provides
    fun provideMovieTypesRepository(
        repository: DefaultMovieTypesRepository
    ): MovieTypesRepository = repository
}
