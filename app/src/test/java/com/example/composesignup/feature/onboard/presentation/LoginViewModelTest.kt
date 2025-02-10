package com.example.composesignup.feature.onboard.presentation

import com.example.composesignup.core.testing.util.MainDispatcherRule
import com.example.composesignup.feature.onboard.domain.usecase.EmailMatcherUseCase
import com.example.composesignup.feature.onboard.domain.usecase.InputValidIUseCase
import com.example.composesignup.feature.onboard.domain.usecase.PasswordMatcherUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class LoginViewModelTest{

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: LoginViewModel
    private lateinit var useCase: InputValidIUseCase
    private lateinit var emailMatcherUseCase: EmailMatcherUseCase
    private lateinit var passwordMatcherUseCase: PasswordMatcherUseCase

    @Before
    fun setUp(){
        emailMatcherUseCase = EmailMatcherUseCase()
        passwordMatcherUseCase = PasswordMatcherUseCase()
        useCase = InputValidIUseCase(emailMatcherUseCase,passwordMatcherUseCase)
        viewModel = LoginViewModel(useCase)
    }

    @Test
    fun uiState_when_uiError_isShow() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        assertEquals(
            null,viewModel.uiState.value.uiText
        )

        assertEquals(
            null,viewModel.uiState.value.exception
        )
        collectJob.cancel()

    }


}