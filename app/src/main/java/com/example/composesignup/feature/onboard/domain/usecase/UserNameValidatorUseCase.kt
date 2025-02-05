package com.example.composesignup.feature.onboard.domain.usecase

import com.example.composesignup.core.utils.TextFieldException

class UserNameValidatorUseCase {
    operator fun invoke(userName:String):FormValidation{

        return if (userName.isBlank()){
            FormValidation(success = false, exception = TextFieldException(
                message = "User Name Cannot Be Empty"
            ))
        } else {
            FormValidation(
                success = true
            )
        }
    }
}