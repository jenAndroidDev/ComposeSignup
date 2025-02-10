package com.example.composesignup.feature.onboard.domain.usecase

data class InputValidIUseCase(
    val emailMatcherUseCase: EmailMatcherUseCase,
    val passwordMatcherUseCase: PasswordMatcherUseCase
)
