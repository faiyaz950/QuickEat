package com.example.quickeat.data


import com.example.quickeat.domain.SearchPlacesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFoursquareApi(): FoursquareApi {
        return Retrofit.Builder()
            .baseUrl("https://api.foursquare.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoursquareApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFoursquareRepository(api: FoursquareApi): FoursquareRepository {
        return FoursquareRepository(api)
    }

    @Provides
    @Singleton
    fun provideSearchPlacesUseCase(repository: FoursquareRepository): SearchPlacesUseCase {
        return SearchPlacesUseCase(repository)
    }
}
