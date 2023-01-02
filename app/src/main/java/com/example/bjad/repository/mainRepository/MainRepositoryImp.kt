package com.example.bjad.repository.mainRepository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bjad.Model.SunsetSetriseModel
import com.example.bjad.Model.User
import com.example.bjad.api.SunsetSunriseApi
import com.example.bjad.util.UiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import retrofit2.Response
import retrofit2.Retrofit

private const val TAG = "MainRepositoryImp"
class MainRepositoryImp(
    val auth: FirebaseAuth,
    val database: FirebaseFirestore,
    val sunsetSunriseApi: SunsetSunriseApi
) : MainRepository{
    private lateinit var uid:String
    private lateinit var user:User
    override fun calendar(latitude: Int, longitude: Int) {

    }



    override fun setUserName(result: (UiState<String>) -> Unit){
        // how to get the current user
        uid = auth.currentUser?.uid.toString()
        var ans:String = ""
        val docReference:DocumentReference = database.collection("user").document(uid)
        docReference.get().addOnSuccessListener {
            if (it != null){
                // how to get the proper data from the firestore
                ans = it.data?.get("userName").toString()

                Log.d(TAG, "setUserName: before invoking")
                result.invoke(UiState.Success(ans))
                Log.d(TAG, "setUserName: ${it.data?.get("userName").toString()}")

            }
        }
    }

    override suspend fun getSunsetSunrise() = sunsetSunriseApi.getTime()

}