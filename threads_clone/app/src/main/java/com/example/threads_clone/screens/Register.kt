package com.example.threads_clone.screens

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.threads_clone.R
import com.example.threads_clone.navigations.Routes
import com.example.threads_clone.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(navController: NavHostController) {
    var name: String by remember {
        mutableStateOf("")
    }
    var username: String by remember {
        mutableStateOf("")
    }
    var email: String by remember {
        mutableStateOf("")
    }
    var bio: String by remember {
        mutableStateOf("")
    }
    var password: String by remember {
        mutableStateOf("")
    }
    var imageUri:Uri? by remember {
        mutableStateOf<Uri?>(null)
    }


    val authViewModel:AuthViewModel= viewModel()
    val firebaseUser by authViewModel.firebaseUser.observeAsState(null)

//    this is to check if the condition is got from the user or not
    val permissionToRequest: String = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_IMAGES
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }


    val context= LocalContext.current

    val launcher= rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){
        uri: Uri? ->
        imageUri=uri
    }
    val permisLauncher:ManagedActivityResultLauncher<String,Boolean>
    = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()){
        isGranted:Boolean ->
        if (isGranted){

        }else{
//            authViewModel.register(email, password, name, bio, username,imageUri!!,context)
        }
    }





    LaunchedEffect(firebaseUser ){
        if (firebaseUser!=null){
            navController.navigate(Routes.BottomNav.routes){
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop=true
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Register here",
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 25.sp
                )
            )
        }
        item {
            Spacer(modifier = Modifier.height(5.dp))
        }
        item {
            Image(
                painter = if (imageUri==null)
                    painterResource(id = R.drawable.man)
                else
                    rememberAsyncImagePainter(model = imageUri),
                contentDescription = "image placehodeer icon",
                modifier = Modifier
                    .size(95.dp)
                    .clip(CircleShape)
                    .background(color = Color.DarkGray)
                    .clickable {
                        val isGranted = ContextCompat.checkSelfPermission(
                            context, permissionToRequest
                        ) == PackageManager.PERMISSION_GRANTED

                        if (isGranted) {
                            launcher.launch("image/*")
                        } else {
                            permisLauncher.launch(permissionToRequest)
                        }
                    })


        }
        item {
            Spacer(modifier = Modifier.height(5.dp))
        }
        item {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            OutlinedTextField(
                value = bio,
                onValueChange = { bio = it },
                label = { Text("bio") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Spacer(modifier = Modifier.height(5.dp))
        }
        item {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Spacer(modifier = Modifier.height(5.dp))
        }
        item {
            ElevatedButton(
                onClick = {

                    if(name.isEmpty()||email.isEmpty()||username.isEmpty()||imageUri==null||bio.isEmpty()){
                        Toast.makeText(context,"please fill all details",Toast.LENGTH_SHORT).show()
                    }else{
                            authViewModel.register(email,password,name,bio,username,imageUri,context)
                        //navController.navigate(Routes.BottomNav.routes)

                    }



                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Register here",
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 25.sp
                    ),
                    // color = Color.Black
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(2.dp))
        }
        item {
            TextButton(
                onClick = {
                          navController.navigate(Routes.Login.routes){
                              popUpTo(navController.graph.startDestinationId)
                              launchSingleTop=true
                          }

                          },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Already registered? Login here",
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 25.sp
                    ),
                    // color = Color.Black
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun reg_prev() {
//
//        Register()
//
//}




























//code with out of the screen problem
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun Register(){
//
//    var name:String by remember{
//        mutableStateOf("")
//    }
//    var username:String by remember{
//        mutableStateOf("")
//    }
//
//    var email:String by remember{
//        mutableStateOf("")
//    }
//
//    var bio:String by remember{
//        mutableStateOf("")
//    }
//    var password:String by remember{
//        mutableStateOf("")
//    }
//
//    Column(modifier= Modifier
//        .fillMaxSize()
//        .padding(10.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ){
//
//        Text(text = "Register here", style = TextStyle(
//            fontWeight = FontWeight.ExtraBold
//            , fontSize = 25.sp
//        )
//        )
//        Spacer(modifier = Modifier.height(30.dp))
//        OutlinedTextField(
//            value = name,
//            onValueChange = { name = it },
//            label = { Text ("Name") },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Email
//            ), singleLine = true,
//            modifier = Modifier.fillMaxWidth()
//        )
//        OutlinedTextField(
//            value = username,
//            onValueChange = { username = it },
//            label = { Text ("Username") },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Email
//            ), singleLine = true,
//            modifier = Modifier.fillMaxWidth()
//        )
//        OutlinedTextField(
//            value = email,
//            onValueChange = { email = it },
//            label = { Text ("Email") },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Email
//            ), singleLine = true,
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(5.dp))
//        OutlinedTextField(
//            value = password,
//            onValueChange = { password = it },
//            label = { Text ("Password") },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Password
//            ), singleLine = true,
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(5.dp))
//        ElevatedButton(onClick = { /*TODO*/ },
//            modifier = Modifier.fillMaxWidth()) {
//            Text(text = "Register here", style = TextStyle(
//                fontWeight = FontWeight.ExtraBold
//                , fontSize = 25.sp),
////                color = Color.Black
//            )
//        }
//
//
//        Spacer(modifier = Modifier.height(2.dp))
//        TextButton(onClick = { /*TODO*/ },
//            modifier = Modifier.fillMaxWidth()) {
//            Text(text = "alredy registers ? login here ", style = TextStyle(
//                fontWeight = FontWeight.Medium
//                , fontSize = 25.sp),
////                color = Color.Black
//            )
//        }
//    }
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun reg_prev(){
//    Register()
//}


//above code was creating pron in horizontal mode content was going out of the screen
