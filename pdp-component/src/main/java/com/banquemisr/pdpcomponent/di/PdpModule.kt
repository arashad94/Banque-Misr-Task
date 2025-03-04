package com.banquemisr.pdpcomponent.di

import com.banquemisr.pdpcomponent.data.api.MovieDetailsApiService
import com.banquemisr.pdpcomponent.data.mapper.*
import com.banquemisr.pdpcomponent.data.mapper.MovieDetailsMapper
import com.banquemisr.pdpcomponent.data.repository.DefaultMovieDetailsRepository
import com.banquemisr.pdpcomponent.domain.repository.MovieDetailsRepository
import com.banquemisr.pdpcomponent.domain.usecase.*
import com.banquemisr.pdpcomponent.domain.usecase.FetchMovieDetailsUseCase
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object PdpModule {
    @Singleton
    @Provides
    fun provideMoviesByTypeService(
        retrofit: Retrofit
    ): MovieDetailsApiService = retrofit.create(MovieDetailsApiService::class.java)

    @Singleton
    @Provides
    fun provideMovieDetailsMapper(): MovieDetailsMapper = DefaultMovieDetailsMapper()

    @Singleton
    @Provides
    fun provideMovieDetailsRepository(
        repository: DefaultMovieDetailsRepository
    ): MovieDetailsRepository = repository

    @Singleton
    @Provides
    fun FetchMovieDetails(
        useCase: FetchMovieDetailsUseCase
    ): FetchMovieDetails = useCase
}
