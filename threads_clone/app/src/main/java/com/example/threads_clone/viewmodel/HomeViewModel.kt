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

class HomeViewModel: ViewModel() {
    private val db=FirebaseDatabase.getInstance()
    val thread=db.getReference("threads")


    private var _threadAndUsers=MutableLiveData<List<Pair<ThreadModel,UserModel>>>()
    val threadAndUsers: LiveData<List<Pair<ThreadModel,UserModel>>> = _threadAndUsers

    init {
        fetchThreadAndUser {
            _threadAndUsers.value =it
        }
    }




//    check wheter we can do this without lambda or not

    private fun fetchThreadAndUser(onResult: (List<Pair<ThreadModel,UserModel>>) -> Unit){
        thread.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val result = mutableListOf<Pair<ThreadModel,UserModel>>()
                for (threadSnapshot in snapshot.children){
                    val thread=threadSnapshot.getValue(ThreadModel::class.java)
                    thread.let {
                        fetchUserFromThread(it!!){
                            user->
                            result.add(0,it to user)
                            if (result.size==snapshot.childrenCount.toInt())
                            {
                                onResult(result)
                            }
                        }
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun fetchUserFromThread(thread: ThreadModel,onResult:(UserModel)->Unit){
        db.getReference("users").child(thread.userId)
            .addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val  user=snapshot.getValue(UserModel::class.java)
                    user?.let(onResult)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

    }
}