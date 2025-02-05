package com.example.composesignup.feature.onboard.domain.usecase

data class InputFormUseCase(
    val userNameUseCase:UserNameValidatorUseCase,
    val userEmailUseCase:EmailValidatorUseCase
)