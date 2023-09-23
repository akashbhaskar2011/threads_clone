package com.example.threads_clone.screens

import android.content.Context
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.threads_clone.navigations.Routes
import com.example.threads_clone.viewmodel.AuthViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavHostController ){


    val context:Context= LocalContext.current



    val authViewModel:AuthViewModel= viewModel()
    val firebaseUser by authViewModel.firebaseUser.observeAsState()
    val error by authViewModel.error.observeAsState()




//
//    LaunchedEffect(error ){
//        if (error){
//            navController.navigate(Routes.BottomNav.routes){
//                popUpTo(navController.graph.startDestinationId)
//                launchSingleTop=true
//            }
//        }
//    }




    LaunchedEffect(firebaseUser ){
        if (firebaseUser!=null){
            navController.navigate(Routes.BottomNav.routes){
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop=true
            }
        }
    }




    var email:String by remember{
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

        Text(text = "Login", style = TextStyle(
            fontWeight = FontWeight.ExtraBold
            , fontSize = 25.sp
        ))
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text ("enter ur email") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ), singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))





        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text ("enter ur Passord") },
            visualTransformation= PasswordVisualTransformation(),

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ), singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )





        Spacer(modifier = Modifier.height(5.dp))
        ElevatedButton(onClick = {


//            error.let {
//                Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
//            }


            error.let {
                Toast.makeText(context,"invalid id /pass",Toast.LENGTH_SHORT).show()
            }

            if(email.isEmpty()||password.isEmpty()){
                Toast.makeText(context,"please fill all details",Toast.LENGTH_SHORT).show()
            }

            else{
                authViewModel.login(email,password,context)
            }

                                 },
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Login", style = TextStyle(
                fontWeight = FontWeight.ExtraBold
                , fontSize = 25.sp),
//                color = Color.Black
            )
        }


        Spacer(modifier = Modifier.height(2.dp))
        TextButton(onClick = {
            navController.navigate(Routes.Register.routes){
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop=true
            }
        }
        ,
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "new user ? create account", style = TextStyle(
                fontWeight = FontWeight.Medium
                , fontSize = 25.sp),
//                color = Color.Black
            )
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun login_prev(){
//    Login()
//}