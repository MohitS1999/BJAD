package com.example.bjad.repository.photosRepository

import android.util.Log
import com.example.bjad.util.UiState
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject


private const val TAG = "PhotosRepositoryImp"
class PhotosRepositoryImp(
    private val database:FirebaseFirestore
):PhotosRepository {

    override suspend fun getPhotos(result: (UiState<ArrayList<String>>) -> Unit) {
        val list:ArrayList<String> = ArrayList<String>()
        database.collection("Photos")
            .addSnapshotListener{ snapshots ,e ->{

            }
                for (dc in snapshots!!.documentChanges) {
                    dc.document.data?.let {
                        it.forEach{(_,value) ->
                            list.add(value.toString())
                        }
                    }
                }

                result.invoke(UiState.Success(list))
            }
    }
}