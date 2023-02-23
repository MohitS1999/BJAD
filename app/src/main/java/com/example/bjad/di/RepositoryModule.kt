package com.example.bjad.di

import android.media.MediaPlayer
import com.example.bjad.api.SunsetSunriseApi
import com.example.bjad.repository.authRepository.AuthRepository
import com.example.bjad.repository.authRepository.AuthRepositoryImp
import com.example.bjad.repository.mainRepository.MainRepository
import com.example.bjad.repository.mainRepository.MainRepositoryImp
import com.example.bjad.repository.photosRepository.PhotosRepository
import com.example.bjad.repository.photosRepository.PhotosRepositoryImp
import com.example.bjad.repository.songsRepository.SongsRepository
import com.example.bjad.repository.songsRepository.SongsRepositoryImp
import com.example.bjad.repository.videoRepository.VideoRepository
import com.example.bjad.repository.videoRepository.VideoRepositoryImp
import com.example.bjad.util.SunsetSunriseBase_url
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        database:FirebaseFirestore,
        auth:FirebaseAuth
    ): AuthRepository {
        return AuthRepositoryImp(auth,database)
    }

    @Provides
    @Singleton
    fun provideMainRepository(
        auth: FirebaseAuth,
        database: FirebaseFirestore,
        sunsetSunriseApi: SunsetSunriseApi
    ) : MainRepository{
        return MainRepositoryImp(auth,database,sunsetSunriseApi)
    }

    @Provides
    @Singleton
    fun providesPhotosRepository(
        database: FirebaseFirestore
    ):PhotosRepository{
        return PhotosRepositoryImp(database)
    }

    @Provides
    @Singleton
    fun providesSongsRepository(
        database: FirebaseFirestore
    ):SongsRepository{
        return SongsRepositoryImp(database)
    }


    @Provides
    @Singleton
    fun providesVideoSongsRepository(
        database:FirebaseFirestore
    ) : VideoRepository {
        return VideoRepositoryImp(database)
    }
}