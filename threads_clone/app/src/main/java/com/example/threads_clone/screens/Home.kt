package com.example.threads_clone.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.threads_clone.item_view.ThreadItem
import com.example.threads_clone.viewmodel.HomeViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Home(navHostController: NavHostController){

    val homeViewModel:HomeViewModel= viewModel()
val context= LocalContext.current
    val threadAndUser by homeViewModel.threadAndUsers.observeAsState(null)
    LazyColumn(){
        item{
            Text(text = "Home")
        }
        items(threadAndUser ?: emptyList()){pairs->
            ThreadItem(thread =pairs.first,pairs.second ,navHostController,FirebaseAuth.getInstance().currentUser!!.uid)
        }




    }

}