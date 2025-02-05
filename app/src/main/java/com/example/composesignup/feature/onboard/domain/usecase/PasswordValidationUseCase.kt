package com.example.composesignup.feature.onboard.domain.usecase

import com.example.composesignup.core.utils.TextFieldException


class PasswordValidationUseCase {
    operator fun invoke(password:String):FormValidation{
        val passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$".toRegex()
        return if (!passwordPattern.matches(password)){
            FormValidation(success = false, exception = TextFieldException(message = "Invalid Password"))
        }else{
            FormValidation(success = true)
        }
    }
}