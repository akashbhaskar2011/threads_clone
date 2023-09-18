package com.example.threads_clone.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.threads_clone.navigations.Routes
import com.example.threads_clone.viewmodel.AuthViewModel

@Composable
fun Profile(navController:NavHostController){

    val authViewModel:AuthViewModel= viewModel()
    val firebaseUser by authViewModel.firebaseUser.observeAsState(null)


    LaunchedEffect(firebaseUser ){
        if (firebaseUser==null){
            navController.navigate(Routes.Login.routes){
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop=true
            }
        }
    }

Button(onClick = { authViewModel.logOut() }) {
    Text(text = "Log out")
}

}