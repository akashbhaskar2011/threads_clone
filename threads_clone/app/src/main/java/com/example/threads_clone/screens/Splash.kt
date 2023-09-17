package com.example.threads_clone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.threads_clone.R
import com.example.threads_clone.navigations.Routes
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun Splash(navController: NavHostController){

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (image:ConstrainedLayoutReference)=createRefs()
        Image(painter = painterResource(id = R.drawable.thread), contentDescription = "",
            modifier = Modifier.constrainAs(image){
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end )

            }.size(120.dp))
    }

    LaunchedEffect(true ){
        delay(3000)

        if(FirebaseAuth.getInstance().currentUser!=null)
        navController.navigate(Routes.BottomNav.routes){
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop=true
        }
        else
            navController.navigate(Routes.Login.routes){
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop=true
            }
    }

//this was to check where the error was coming that was causing the app to crash
//    LaunchedEffect(true) {
//        try {
//            delay(3000)
//
//            // Check Firebase Authentication state
//            if (FirebaseAuth.getInstance().currentUser != null) {
//                // If the user is authenticated, navigate to the desired destination
//                navController.navigate(Routes.BottomNav.routes)
//            } else {
//                // If the user is not authenticated, navigate to the login screen
//                navController.navigate(Routes.Login.routes)
//            }
//        } catch (e: Exception) {
//            // Handle any exceptions here
//            // You can log the error for debugging purposes
//            Log.e("NavigationError", "Error during navigation: ${e.message}")
//        }
//    }


}