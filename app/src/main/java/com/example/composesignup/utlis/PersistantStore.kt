package com.example.composesignup.utlis

interface PersistentStore {

    val isUserLoggedIn:Boolean
    val signUpStep:Int
    val name:String
    val email:String
    val password:String
    val isWelcomeScreenShown:Boolean

    fun setUserName(name:String)
    fun setUserEmail(email:String)
    fun setUserPassword(password:String)
    fun setUserLoginStatus(logged:Boolean)
    fun setSignUpStatus(step:Int)
    fun setWelcomeScreenStatus(shown:Boolean)
    fun logout()
}