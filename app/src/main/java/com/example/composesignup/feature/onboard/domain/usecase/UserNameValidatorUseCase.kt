package com.example.composesignup.feature.onboard.domain.usecase

import com.example.composesignup.core.utils.TextFieldException

class UserNameValidatorUseCase {
    operator fun invoke(userName:String):FormValidation{

        return if (userName.isEmpty()){
            FormValidation(success = false, exception = TextFieldException(
                message = "user name cannot be empty"
            ))
        } else {
            FormValidation(
                success = true
            )
        }
    }
}