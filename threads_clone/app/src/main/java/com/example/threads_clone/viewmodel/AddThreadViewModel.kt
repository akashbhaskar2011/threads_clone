package com.example.threads_clone.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.threads_clone.Model.ThreadModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.UUID

class AddThreadViewModel: ViewModel() {
    private val db=FirebaseDatabase.getInstance()
    val userRef=db.getReference("threads")


    private val _isPosted=MutableLiveData<Boolean>()
    val isposted: LiveData<Boolean> = _isPosted


    private  val storageRef=Firebase.storage.reference
    private val imageRef=storageRef.child("threads/${UUID.randomUUID()}.jpg")





    fun saveImage(
        thread:String,
        userId:String,
        imageUri:Uri
   )
    {
        val uploadTask=imageRef.putFile(imageUri)
        uploadTask.addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener {
                saveData(thread,userId,it.toString() )
            }

        }
    }

    fun saveData(
        thread:String,
        userId:String,
        image :String,
    ) {
        val threadData=ThreadModel(thread,image,userId,System.currentTimeMillis().toString(),)
        userRef.child(userRef.push().key!!).setValue(threadData)
            .addOnSuccessListener {
                _isPosted.postValue(true)
            }
            .addOnFailureListener{
                _isPosted.postValue(false)
            }
    }

}