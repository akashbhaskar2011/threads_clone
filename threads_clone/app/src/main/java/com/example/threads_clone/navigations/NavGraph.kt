package com.example.threads_clone.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.threads_clone.screens.AddThreads
import com.example.threads_clone.screens.BottomNav
import com.example.threads_clone.screens.Home
import com.example.threads_clone.screens.Login
import com.example.threads_clone.screens.Notification
import com.example.threads_clone.screens.Profile
import com.example.threads_clone.screens.Register
import com.example.threads_clone.screens.Search
import com.example.threads_clone.screens.Splash

@Composable
fun NavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Routes.Splash.routes){
        composable(route = Routes.Splash.routes){
            Splash(navController = navController)
        }

        composable(route = Routes.AddThreads.routes){
            AddThreads()
        }
        composable(route = Routes.Home.routes){
            Home()
        }
        composable(route = Routes.Notification.routes){
            Notification()
        }
        composable(route = Routes.Profile.routes){
            Profile(navController)
        }
        composable(route = Routes.Search.routes){
            Search()
        }
        composable(route = Routes.BottomNav.routes){
            BottomNav(navController)
        }
        composable(route = Routes.Login.routes){
           Login(navController)
        }
        composable(route = Routes.Register.routes){
           Register(navController)
        }
    }
}


