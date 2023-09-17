package com.example.threads_clone.navigations

sealed class Routes(val routes:String){
    object Home :Routes(routes = "Home")
    object Notification :Routes(routes = "Notification")
    object AddThreads :Routes(routes = "AddThreads")
    object Profile :Routes(routes = "Profile")
    object Search :Routes(routes = "Search")
    object Splash :Routes(routes = "Splash")
    object BottomNav :Routes(routes = "BottomNav ")
    object Login :Routes(routes = "Login")
    object Register :Routes(routes = "Register")
}
