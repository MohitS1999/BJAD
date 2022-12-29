package com.example.bjad.di

import com.example.bjad.api.SunsetSunriseApi
import com.example.bjad.util.SunsetSunriseBase_url
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {


    //creating the instance of FirebaseFirestore
    @Provides
    @Singleton
    fun provideFireStoreInstance():FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

    //creating the instance of Firebase Auth
    @Provides
    @Singleton
    fun provideFirebaseAuthInstance():FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    // creating the instance of retrofit api
    @Provides
    @Singleton
    fun provideRetrofit(): SunsetSunriseApi =
        Retrofit.Builder()
            .baseUrl(SunsetSunriseBase_url.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SunsetSunriseApi::class.java)




}