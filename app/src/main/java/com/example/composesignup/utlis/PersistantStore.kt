package com.example.composesignup.utlis

interface PersistentStore {

    val isUserLoggedIn:Boolean
    val signUpStep:Int

    fun setUserLoginStatus(logged:Boolean)
    fun setSignUpStatus(step:Int)
    fun logout()
}