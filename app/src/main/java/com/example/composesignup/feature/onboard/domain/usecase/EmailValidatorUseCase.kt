package com.example.composesignup.feature.onboard.domain.usecase

import com.example.composesignup.core.utils.TextFieldException
import java.text.Normalizer.Form

/*
* Test the below use case using Junit4 and if it's working move it to the core package*/
class EmailValidatorUseCase {
    operator fun invoke(email:String):FormValidation{
        if (email.isBlank()){
            return FormValidation(success = false, exception = TextFieldException(
                message = "Email Cannot be Empty"
            ))
        }
        val emailPattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        return if (email.matches(emailPattern)) {
            FormValidation(success = true)
        } else {
            FormValidation(success = false, exception = TextFieldException(
                message = "Invalid Email Pattern"
            ))
        }
    }
}
data class ValidationResult(
    val success:Boolean,
    val errorMessage:String
)

