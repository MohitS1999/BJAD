package com.example.bjad.di

import com.example.bjad.repository.authRepository.AuthRepository
import com.example.bjad.repository.authRepository.AuthRepositoryImp
import com.example.bjad.repository.mainRepository.MainRepository
import com.example.bjad.repository.mainRepository.MainRepositoryImp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
        database: FirebaseFirestore
    ) : MainRepository{
        return MainRepositoryImp(auth,database)
    }
}