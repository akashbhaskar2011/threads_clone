package com.example.threads_clone.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE

object SharedPref {
    fun storeData( email: String,
                   name: String,
                   bio: String,
                   username: String,
                   toString: String,//image url
                   context: Context) {
        val sharedPreferences=context.getSharedPreferences("users",MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.putString("name",name)
        editor.putString("email",email)
        editor.putString("bio",bio)
        editor.putString("username",username)
        editor.putString("tostring",toString)
            editor.apply()
    }

    fun getUserName(context: Context):String{
        val sharedPreferences=context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPreferences.getString("username","")!!
    }
    fun getName(context: Context):String{
        val sharedPreferences=context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPreferences.getString("name","")!!
    }

    fun getbio(context: Context):String{
        val sharedPreferences=context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPreferences.getString("bio","")!!
    }

    fun getEmail(context: Context):String{
        val sharedPreferences=context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPreferences.getString("emial","")!!
    }

    fun getImageUrl(context: Context):String{
        val sharedPreferences=context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPreferences.getString("tostring","")!!
    ///to string we use the variable as iamgeurl but somewhere i have used to string so i am using to string everywhere
    }

}