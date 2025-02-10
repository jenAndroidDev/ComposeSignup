package com.example.composesignup.feature.onboard.domain.usecase

import com.example.composesignup.core.utils.TextFieldException

class PasswordMatcherUseCase {
    operator fun invoke(savedPassword:String,inputPassword:String):FormValidation{
       return if (savedPassword==inputPassword){
            FormValidation(success = true, exception = TextFieldException(message = "Password Matching"))
        }else{
            FormValidation(success = false, exception = TextFieldException(message = "Password MisMatching"))
        }
    }
}