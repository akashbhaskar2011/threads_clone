package com.example.threads_clone.item_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.threads_clone.Model.ThreadModel
import com.example.threads_clone.Model.UserModel

@Composable
fun ThreadItem(thread: ThreadModel
               ,users: UserModel,
                navHostController: NavHostController,
               userId:String){

    val context= LocalContext.current
    Column (modifier=Modifier.fillMaxSize()){


    Row(modifier= Modifier.padding(5.dp)) {
        Image(
            painter = rememberAsyncImagePainter(model = users.imageUrl),
            "user profile pic", Modifier
                .padding(5.dp)
                .size(40.dp)
                .clip(CircleShape)
                .clickable {

                }, contentScale = ContentScale.Crop
        )
//SharedPref.getUserName(context) put this ,for now to test ui using some random text
        Text(
            text = users.username, style = TextStyle(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
            ), modifier = Modifier.padding(start = 10.dp, top = 5.dp)
        )

    }
        Text(
            text = thread.thread, style = TextStyle(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
            ), modifier = Modifier.padding(start = 10.dp,top=5.dp)
        )


//        for testing using SharedPref.getImageUrl(context)
        if(thread.image!=""){
        Card(
            modifier = Modifier
                .padding(all = 5.dp)
                .fillMaxWidth()
                .height(250.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = thread.image),
                    contentDescription = "user profile pic",
                    contentScale = ContentScale.Crop
                )
            }
        }

        }

        Divider(color = Color.Gray)
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun PrevAddThread(){
////    ThreadItem()
//}