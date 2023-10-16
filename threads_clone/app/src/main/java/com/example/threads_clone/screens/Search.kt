package com.example.threads_clone.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import com.example.threads_clone.item_view.ThreadItem
import com.example.threads_clone.item_view.UserItem
import com.example.threads_clone.viewmodel.HomeViewModel
import com.example.threads_clone.viewmodel.SeachViewModel
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(navHostController:NavHostController){

    var search by remember {
        mutableStateOf("")
    }
    val seachViewModel: SeachViewModel = viewModel()
    val context= LocalContext.current
    val userList by seachViewModel.usersList.observeAsState(null)
    Column (modifier= Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(10.dp)){

        Text(text = "Search")

        OutlinedTextField(
            value = search,
            onValueChange = {search= it },
            label = { Text ("Search user") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search icon")
            }
        )

        LazyColumn(){


if(userList!=null && userList!!.isNotEmpty()){
    val filterItems=userList!!.filter { it.username!!.contains(search, ignoreCase = true) }
    items(filterItems ?: emptyList()){user ->
        UserItem(
            user
            ,navHostController)
    }
}





        }
    }

}
