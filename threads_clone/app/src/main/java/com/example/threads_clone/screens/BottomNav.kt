package com.example.threads_clone.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.threads_clone.Model.BottomNavItem
import com.example.threads_clone.navigations.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNav(navController:NavHostController){
    val navController1= rememberNavController()
    Scaffold(bottomBar = {MyBottomBar(navController1)}) {innerPadding->
        NavHost(navController=navController1, startDestination = Routes.Home.routes,
            modifier = Modifier.padding(innerPadding)){
            composable(route = Routes.Search.routes){
                Search(navController)
            }
            composable(route = Routes.AddThreads.routes){
                AddThreads(navController1)
            }
            composable(route = Routes.Home.routes){
                Home(navController)
            }
            composable(route = Routes.Notification.routes){
                Notification()
            }
            composable(route = Routes.Profile.routes){
                Profile(navController)
            }


        }
    }
}

@Composable
fun MyBottomBar(navController1: NavHostController) {

    val backStackEntry=navController1.currentBackStackEntryAsState()

    val list= listOf(
        BottomNavItem(
            title="Home",
            Routes.Home.routes,
            Icons.Rounded.Home
        ),
        BottomNavItem(
            title="Search",
            Routes.Search.routes,
            Icons.Rounded.Search
        ),
        BottomNavItem(
            title="Add Threads",
            Routes.AddThreads.routes,
            Icons.Rounded.Add
        ),
        BottomNavItem(
            title="Notification",
            Routes.Notification.routes,
            Icons.Rounded.Notifications
        ),

        BottomNavItem(
            title="Profile",
            Routes.Profile.routes,
            Icons.Rounded.Person
        ),


    )
    BottomAppBar{
        list.forEach{
            val selected:Boolean=it.routes==backStackEntry?.value?.destination?.route
            NavigationBarItem(selected = selected,
                onClick = {
                    navController1.navigate(it.routes){
                        popUpTo(navController1.graph.findStartDestination().id){
                            saveState=true
                        }
                        launchSingleTop=true
                    }
            }, icon = { 
                Icon(imageVector = it.icon, contentDescription =it.title )
                })
        }
    }
}
