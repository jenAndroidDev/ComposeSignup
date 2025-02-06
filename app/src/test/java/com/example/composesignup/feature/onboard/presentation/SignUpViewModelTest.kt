package com.example.composesignup.feature.onboard.presentation

import com.example.composesignup.core.testing.util.MainDispatcherRule
import com.example.composesignup.feature.onboard.domain.usecase.EmailValidatorUseCase
import com.example.composesignup.feature.onboard.domain.usecase.InputFormUseCase
import com.example.composesignup.feature.onboard.domain.usecase.PasswordValidationUseCase
import com.example.composesignup.feature.onboard.domain.usecase.UserNameValidatorUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpViewModelTest{

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: SignUpViewModel
    private lateinit var emailValidatorUseCase: EmailValidatorUseCase
    private lateinit var userNameValidatorUseCase: UserNameValidatorUseCase
    private lateinit var passwordValidationUseCase: PasswordValidationUseCase
    private lateinit var useCase: InputFormUseCase

    companion object{
        private const val USER_EMAIL = "rjjeninjoseph@gmail.com"
        private const val USER_NAME = "Jenin Joseph R J"
        private const val PASSWORD = "JENINJOSEPH"
    }

    @Before
    fun setUp() {
        emailValidatorUseCase = EmailValidatorUseCase()
        userNameValidatorUseCase = UserNameValidatorUseCase()
        passwordValidationUseCase = PasswordValidationUseCase()
        useCase = InputFormUseCase(userNameUseCase = userNameValidatorUseCase,
            userEmailUseCase = emailValidatorUseCase, passwordUseCase = passwordValidationUseCase)
        viewModel = SignUpViewModel(useCase)
    }

    @Test
    fun uiState_whenEmail_isEntered_then_emailShouldBeUpdated() = runTest {
        viewModel.action(SignUpUiAction.Email(USER_EMAIL))
        assertEquals(USER_EMAIL,viewModel.email)
    }

    @Test
    fun uiState_whenUserName_isEntered_then_nameShouldBeUpdated() = runTest {
        viewModel.action.invoke(SignUpUiAction.UserName(USER_NAME))
        assertEquals(USER_NAME,viewModel.userName)
    }

    @Test
    fun uiState_whenPassword_isEntered_then_passwordShouldBeUpdated() = runTest {
        viewModel.action.invoke(SignUpUiAction.Password(PASSWORD))
        assertEquals(PASSWORD,viewModel.password)
    }



}