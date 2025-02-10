package com.example.composesignup.feature.onboard.domain.usecase

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test

class PasswordMatcherUseCaseTest {

    private lateinit var emailMatcherUseCase: EmailMatcherUseCase
    private lateinit var passwordMatcherUseCase: PasswordMatcherUseCase
    private lateinit var inputValidIUseCase: InputValidIUseCase
    private val EnteredPassword = "jenin"
    private val SavedPassword = "jenin"
    @Before
    fun setUp() {
        emailMatcherUseCase = EmailMatcherUseCase()
        passwordMatcherUseCase = PasswordMatcherUseCase()
        inputValidIUseCase = InputValidIUseCase(emailMatcherUseCase,passwordMatcherUseCase)
    }

    @Test
    fun verify_enteredPassword_matches_withSavedPassword() = runTest {
        val result = passwordMatcherUseCase.invoke(EnteredPassword,SavedPassword)
        assertThat(result.success)
    }
}