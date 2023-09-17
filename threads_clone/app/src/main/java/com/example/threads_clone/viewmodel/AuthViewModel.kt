package com.example.threads_clone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel: ViewModel() {
    val auth= FirebaseAuth.getInstance()
    private val db=FirebaseDatabase.getInstance()
    val userRef=db.getReference("users")

    private val _firebaseUser=MutableLiveData<FirebaseUser>()
    val firebaseUser: LiveData<FirebaseUser> = _firebaseUser

    private val _error=MutableLiveData<String>()
    val error: LiveData<String> = _error


    init {
        _firebaseUser.value=auth.currentUser
    }

    fun login(email:String,password:String){

        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
            if (it.isSuccessful){
                _firebaseUser.postValue(auth.currentUser)
            }else{
                _error.postValue("Something went wrong")
            }
        }
    }


    fun register(email:String,password:String,name:String,bio:String,username:String){

        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    _firebaseUser.postValue(auth.currentUser)
                }else{
                    _error.postValue("Something went wrong")
                }
            }
    }

}