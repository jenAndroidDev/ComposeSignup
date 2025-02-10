package com.example.composesignup.feature.onboard.domain.usecase


import com.example.composesignup.core.utils.TextFieldException

class EmailMatcherUseCase {
    operator fun invoke(preferenceEmail:String,inputEmail:String):FormValidation{
        return if (preferenceEmail==inputEmail){
           FormValidation(success = true, exception = TextFieldException(message = "Email Matching"))
        }else{
            FormValidation(success = false, exception = TextFieldException(message = "Email MisMatching"))
        }
    }
}