package com.example.composesignup.feature.onboard.domain.usecase


import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class PasswordMatcherUseCaseTest {

    private lateinit var emailMatcherUseCase: EmailMatcherUseCase
    private lateinit var passwordMatcherUseCase: PasswordMatcherUseCase
    private lateinit var inputValidIUseCase: InputValidIUseCase
    private val validInputPassword = "jenin"
    private val validSavedPassword = "jenin"
    private val inValidInputPassword ="joseph"
    private val invalidSavedPassword = "joseph"


    @Before
    fun setUp() {
        emailMatcherUseCase = EmailMatcherUseCase()
        passwordMatcherUseCase = PasswordMatcherUseCase()
        inputValidIUseCase = InputValidIUseCase(emailMatcherUseCase,passwordMatcherUseCase)
    }

    @Test
    fun verify_enteredPassword_matches_withSavedPassword() = runTest {
        val result = passwordMatcherUseCase.invoke(validInputPassword,validSavedPassword)

        assertEquals(
            true,
            result.success)
    }

    @Test
    fun verify_enteredPassword_misMatches_withSavedPassword() = runTest {
        val result = passwordMatcherUseCase.invoke(inValidInputPassword,validSavedPassword)

        assertEquals(
            false,
            result.success
        )
    }

}