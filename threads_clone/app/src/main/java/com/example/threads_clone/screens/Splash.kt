package com.example.threads_clone.screens

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import com.example.threads_clone.R
import com.example.threads_clone.navigations.Routes
import kotlinx.coroutines.delay
import org.w3c.dom.Text

@Composable
fun Splash(navController: NavHostController){

    Image(painter = painterResource(id = R.drawable.thread), contentDescription = "")
    LaunchedEffect(true ){
        delay(3000)
        navController.navigate(Routes.BottomNav.routes)
    }

}