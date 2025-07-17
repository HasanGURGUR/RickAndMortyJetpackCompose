package com.hasangurgur.rickandmortycompose.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hasangurgur.rickandmortycompose.data.remote.CharacterApi
import com.hasangurgur.rickandmortycompose.data.repository.CharacterRepositoryImpl
import com.hasangurgur.rickandmortycompose.data.repository.EpisodeRepositoryImpl
import com.hasangurgur.rickandmortycompose.domain.repository.CharacterRepository
import com.hasangurgur.rickandmortycompose.domain.repository.EpisodeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    @Provides
    @Singleton
    fun provideGson() : Gson = GsonBuilder().create()


    @Provides
    @Singleton
    fun provideRetrofit(gson : Gson) : Retrofit = 
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideCharacterApi(retrofit: Retrofit) : CharacterApi = 
        retrofit.create(CharacterApi::class.java)

    @Provides
    @Singleton
    fun provideCharacterRepository(api : CharacterApi): CharacterRepository =
        CharacterRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideEpisodeRepository(api: CharacterApi): EpisodeRepository =
       EpisodeRepositoryImpl(api)

}