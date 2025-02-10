package com.example.composesignup.feature.onboard.domain.usecase

import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class EmailMatcherUseCaseTest {

    private lateinit var emailMatcherUseCase: EmailMatcherUseCase
    private val validInputEmail = "jenin@gmail.com"
    private val validPreferenceEmail = "jenin@gmail.com"
    private val inValidInputEmail = "jeninjoseph@gmail.com"

    @Before
    fun setUp() {
        emailMatcherUseCase = EmailMatcherUseCase()
    }

    @Test
    fun verify_enteredEmail_matches_withSavedEmail() = runTest {
        val result = emailMatcherUseCase.invoke(validPreferenceEmail,validInputEmail)

        assertEquals(
            true,
            result.success
        )
    }

    @Test
    fun verify_enteredEmail_misMatches_withSavedEmail() = runTest {
        val result = emailMatcherUseCase.invoke(inValidInputEmail,validPreferenceEmail)

        assertEquals(
            false,
            result.success
        )
    }
}