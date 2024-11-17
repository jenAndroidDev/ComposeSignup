package com.example.composesignup.utlis

import android.content.Context
import android.provider.Contacts.Intents.UI
import androidx.annotation.StringRes
import com.example.composesignup.R
import com.example.composesignup.feature.onboard.presentation.SignUpScreen

sealed class UiText{
    data class DynamicString(val value:String):UiText()
    class StringResource(
        @StringRes val resId:Int,
        vararg val args:Any
    ):UiText()

    fun asString(context: Context):String{
        return when(this){
            is DynamicString->value
            is StringResource->context.getString(resId,*args)
        }
    }
    internal companion object{
        internal val unKnownError:UiText = StringResource(R.string.unknown_error)
        internal val someThingWentWrong:UiText = StringResource(R.string.something_went_wrong_try_later)
        internal val noInternet:UiText = StringResource(R.string.check_your_internet)
    }
}