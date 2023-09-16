package com.example.threads_clone.screens

import android.widget.ScrollView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(){

    var name:String by remember{
        mutableStateOf("")
    }
    var username:String by remember{
        mutableStateOf("")
    }

    var email:String by remember{
        mutableStateOf("")
    }

    var bio:String by remember{
        mutableStateOf("")
    }
    var password:String by remember{
        mutableStateOf("")
    }

    Column(modifier= Modifier
        .fillMaxSize()
        .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(text = "Register here", style = TextStyle(
            fontWeight = FontWeight.ExtraBold
            , fontSize = 25.sp
        )
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text ("Name") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ), singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text ("Username") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ), singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text ("Email") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ), singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text ("Password") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ), singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))
        ElevatedButton(onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Register here", style = TextStyle(
                fontWeight = FontWeight.ExtraBold
                , fontSize = 25.sp),
//                color = Color.Black
            )
        }


        Spacer(modifier = Modifier.height(2.dp))
        TextButton(onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "alredy registers ? login here ", style = TextStyle(
                fontWeight = FontWeight.Medium
                , fontSize = 25.sp),
//                color = Color.Black
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun reg_prev(){
    Register()
}