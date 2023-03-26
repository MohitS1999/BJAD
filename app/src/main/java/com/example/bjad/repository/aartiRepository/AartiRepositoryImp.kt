package com.example.bjad.repository.aartiRepository

import android.util.Log
import com.example.bjad.Model.AartiData
import com.example.bjad.util.UiState
import com.google.firebase.firestore.FirebaseFirestore

private const val TAG = "AartiRepositoryImp"
class AartiRepositoryImp(
    private val database: FirebaseFirestore
) : AartiRepository {
    private lateinit var aartiList: ArrayList<AartiData>
    override suspend fun getAartiList(result: (UiState.Success<ArrayList<AartiData>>) -> Unit) {
        aartiList = ArrayList()

        database.collection("Aarti")
            .addSnapshotListener{ snapshots,e->{

            }
                Log.d(TAG, "getAartiList: ")
                for (document in snapshots!!){
                    aartiList.add(AartiData(
                        document.data.get("name").toString(),
                        document.data.get("image_url").toString(),
                        document.data.get("aarti_url").toString())
                    )
                }
                result.invoke(UiState.Success(aartiList))
            }
    }
}