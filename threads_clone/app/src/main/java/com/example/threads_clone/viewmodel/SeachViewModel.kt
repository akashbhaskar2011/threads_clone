package com.example.threads_clone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.threads_clone.Model.ThreadModel
import com.example.threads_clone.Model.UserModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SeachViewModel: ViewModel() {
    private val db=FirebaseDatabase.getInstance()
    val users=db.getReference("users")


    private var _users=MutableLiveData<List<UserModel>>()
    val usersList: LiveData<List<UserModel>> = _users

    init {
        fetchUser {
            _users.value =it
        }
    }




//    check wheter we can do this without lambda or not

    private fun fetchUser(onResult: (List<UserModel>) -> Unit){
        users.addValueEventListener(object :ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                val result = mutableListOf<UserModel>()
                for (threadSnapshot in snapshot.children){
                    val thread=threadSnapshot.getValue(UserModel::class.java)
                    result.add(thread!!)

                }
                onResult(result)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

//    fun fetchUserFromThread(thread: ThreadModel,onResult:(UserModel)->Unit){
//        db.getReference("users").child(thread.userId)
//            .addListenerForSingleValueEvent(object :ValueEventListener{
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    val  user=snapshot.getValue(UserModel::class.java)
//                    user?.let(onResult)
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    TODO("Not yet implemented")
//                }
//
//            })
//
//    }
}