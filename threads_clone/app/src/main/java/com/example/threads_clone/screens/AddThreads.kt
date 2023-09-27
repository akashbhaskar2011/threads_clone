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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.threads_clone.R
import com.example.threads_clone.navigations.Routes
import com.example.threads_clone.utils.SharedPref
import com.example.threads_clone.viewmodel.AddThreadViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AddThreads(navHostController: NavHostController){

    val threadViewModel:AddThreadViewModel= viewModel()
    val isPosted by threadViewModel.isposted.observeAsState(false)
    val context= LocalContext.current

    var thread:String by remember {
        mutableStateOf("")
    }

    var imageUri: Uri? by remember {
        mutableStateOf<Uri?>(null)
    }


    val permissionToRequest: String = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_IMAGES
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }


    val launcher= rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){
            uri: Uri? ->
        imageUri=uri
    }
    val permisLauncher: ManagedActivityResultLauncher<String, Boolean>
            = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()){
            isGranted:Boolean ->
        if (isGranted){

        }else{
//            authViewModel.register(email, password, name, bio, username,imageUri!!,context)
        }
    }





    LaunchedEffect(isPosted ){
        if(isPosted!!){
            thread=""
            imageUri=null
            Toast.makeText(context,"thread added",Toast.LENGTH_SHORT).show()

navHostController.navigate(Routes.Home.routes){
    popUpTo(Routes.AddThreads.routes){
        inclusive=true
    }

}


        }
    }


    Column(modifier= Modifier
        .fillMaxSize()
            ) {

        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Box {
                Image(painter = painterResource(id = R.drawable.baseline_close_24),
                    contentDescription ="close icon",
                    modifier= Modifier
                        .padding(10.dp)
                        .clickable {
                            navHostController.navigate(Routes.Home.routes) {
                                popUpTo(Routes.AddThreads.routes) {
                                    inclusive = true
                                }
                            }
                        })
            }


            Text(text = "Add Thred", style = TextStyle(
                fontWeight = FontWeight.ExtraBold
                , fontSize = 25.sp,),modifier=Modifier.padding(top=5.dp)
            )


        }

        Row(modifier=Modifier.padding(5.dp)) {
            Image(
                painter = rememberAsyncImagePainter(model = SharedPref.getImageUrl(context)),
                "user profile pic", Modifier
                    .padding(5.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable {

                    }, contentScale= ContentScale.Crop
            )
//SharedPref.getUserName(context) put this ,for now to test ui using some random text
            Text(text = SharedPref.getUserName(context), style = TextStyle(
                fontWeight = FontWeight.ExtraBold
                , fontSize = 20.sp,),modifier=Modifier.padding(start=10.dp,top=5.dp)
            )



        }
        BasicTextFieldWithHint(hint ="Start a thread" , value = thread,
            onValueChange = {thread=it}, modifier = Modifier.padding(8.dp) )

        if(imageUri==null){
            Image(painter = painterResource(id = R.drawable.baseline_attachment_24),
                contentDescription ="close icon",
                modifier= Modifier
                    .padding(10.dp)
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
        }else{
            Box (modifier= Modifier
                .background(Color.Gray)
                .padding(12.dp)
                .height(250.dp)){
                Image(
                    painter = rememberAsyncImagePainter(model =imageUri),
                    "user profile pic", Modifier
                        .padding(5.dp)
                        .fillMaxSize()
                       , contentScale= ContentScale.Crop
                )
                Icon(imageVector = Icons.Default.Close, contentDescription ="close icon" ,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clickable {
                            imageUri = null
                        })
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Row {
            Text(text = "Any one can reply", style = TextStyle(
                fontWeight = FontWeight.Bold
                , fontSize = 20.sp,),modifier=Modifier.padding(top=5.dp)
            )
            TextButton(onClick = {
    if (imageUri==null) {
        threadViewModel.saveData(thread,FirebaseAuth.getInstance().currentUser!!.uid,"")
    }else{
    threadViewModel.saveImage(thread,FirebaseAuth.getInstance().currentUser!!.uid, imageUri!!)
}

            }) {
                Text(text = "Post",fontSize = 20.sp, modifier = Modifier.padding(bottom = 5.dp))

            }
        }

    }

}

@Composable
fun BasicTextFieldWithHint(
   hint :String, value: String ,onValueChange :(String)->Unit,modifier: Modifier
){
    Box(modifier=modifier){
        if(value.isEmpty()){
            Text(text = hint, color = Color.Gray)
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle.Default.copy(color = Color.White),
            modifier = Modifier.fillMaxWidth()
        )

    }
}

//