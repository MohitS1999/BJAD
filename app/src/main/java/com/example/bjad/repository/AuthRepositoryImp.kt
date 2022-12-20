package com.example.bjad.repository

import com.example.bjad.Model.User
import com.example.bjad.util.FireStoreCollection
import com.example.bjad.util.UiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore

class AuthRepositoryImp(
    val auth:FirebaseAuth,
    val database:FirebaseFirestore
) : AuthRepository{

    override fun registerUser(
        email:String,
        password:String,
        user: User, result: (UiState<String>) -> Unit) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    updateUserInfo(user){   state ->
                        when(state){
                            is UiState.Success -> {
                                result.invoke(
                                    UiState.Success("User register successfully")
                                )
                            }
                            is UiState.Failure -> {
                                result.invoke(UiState.Failure(state.error))
                            }
                            else -> {}
                        }
                    }
                }else{
                    try{
                        throw it.exception ?: java.lang.Exception("Invalid authentication")
                    }catch (e : FirebaseAuthWeakPasswordException){
                        result.invoke(UiState.Failure("Authentication failed, Password should be at least 6 characters"))
                    } catch (e : FirebaseAuthInvalidCredentialsException){
                        result.invoke(UiState.Failure("Authentication failed, Invalid Email entered"))
                    }catch (e : FirebaseAuthUserCollisionException){
                        result.invoke(UiState.Failure("Authentication failed, Email already registered."))
                    }catch (e : Exception){
                        result.invoke(UiState.Failure(e.message))
                    }

                }
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override fun updateUserInfo(user: User, result: (UiState<String>) -> Unit) {
        val document = database.collection(FireStoreCollection.USER).document()
        user.id = document.id
        document
            .set(user)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success("Data has been updated successfully")
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override fun loginUser(user: User, result: (UiState<String>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun forgotPassword(user: User, result: (UiState<String>) -> Unit) {
        TODO("Not yet implemented")
    }
}