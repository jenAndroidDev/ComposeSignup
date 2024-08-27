package com.example.composesignup.commons

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShowShortToast(
    context: Context = LocalContext.current,
    text:String,
    ){
    Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
}

@Composable
fun ShowLongToast(
    context: Context,
    text: String
){
    Toast.makeText(context,text,Toast.LENGTH_LONG).show()
}