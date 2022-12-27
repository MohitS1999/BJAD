package com.example.bjad.repository.mainRepository

import android.util.Log
import com.example.bjad.Model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay

private const val TAG = "MainRepositoryImp"
class MainRepositoryImp(
    val auth: FirebaseAuth,
    val database: FirebaseFirestore
) : MainRepository{
    private lateinit var uid:String
    private lateinit var user:User
    override fun calendar(latitude: Int, longitude: Int) {

    }

    override fun setUserName(): String {
        // how to get the current user
        uid = auth.currentUser?.uid.toString()
        var ans:String = ""
        val docReference:DocumentReference = database.collection("user").document(uid)
        docReference.get().addOnSuccessListener {
            if (it != null){
                // how to get the proper data from the firestore
                ans = it.data?.get("userName").toString()
                Log.d(TAG, "setUserName: ${it.data?.get("userName").toString()}")

            }
        }



        Log.d(TAG, "setUserName "+ans)
        return ans
    }
}