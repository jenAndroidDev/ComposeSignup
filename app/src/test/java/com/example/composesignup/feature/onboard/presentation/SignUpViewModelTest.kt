package com.example.composesignup.feature.onboard.presentation

import com.example.composesignup.core.testing.util.MainDispatcherRule
import com.example.composesignup.feature.onboard.domain.usecase.EmailValidatorUseCase
import com.example.composesignup.feature.onboard.domain.usecase.InputFormUseCase
import com.example.composesignup.feature.onboard.domain.usecase.PasswordValidationUseCase
import com.example.composesignup.feature.onboard.domain.usecase.UserNameValidatorUseCase
import com.example.composesignup.utlis.UiText
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

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
        private const val CONFIRM_PASSWORD = "JENINJOSEPH"
        private const val INCORRECT_PASSWORD = "RJJENINJOSEPH"
        private const val ACCEPT_TERMS_AND_CONDTIONS = true
        private const val INPUT_VALID = "Successfully Created Account.Please Login In."
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

    @Test
    fun uiState_whenPasswordReEntered_then_confirmPasswordShouldBeUpdated() = runTest {
        viewModel.action.invoke(SignUpUiAction.ConfirmPassword(CONFIRM_PASSWORD))
        assertEquals(CONFIRM_PASSWORD,viewModel.confirmPassword)
    }

    @Test
    fun uiState_check_ifPasswords_areSame() = runTest{
        viewModel.action.invoke(SignUpUiAction.Password(PASSWORD))
        viewModel.action.invoke(SignUpUiAction.ConfirmPassword(CONFIRM_PASSWORD))

        assertEquals(viewModel.password,viewModel.confirmPassword)
    }

    @Test
    fun uiState_when_uiError_isShown() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()){viewModel.uiState.collect()}

        assertEquals(null,
            viewModel.uiState.value.exception)

        assertEquals( null,
            viewModel.uiState.value.uiText)

        collectJob.cancel()
    }

    @Test
    fun uiState_when_termsAndConditions_areAccepted() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        val initialState = viewModel.uiState.value.isTermsAccepted
        viewModel.action.invoke(SignUpUiAction.ToggleTermsAndCondition)

        assertEquals(
            !initialState,viewModel.uiState.value.isTermsAccepted
        )

        collectJob.cancel()
    }

    @Test
    fun uiState_whenInput_isValid_succesful_signUp() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        viewModel.action.invoke(SignUpUiAction.UserName(USER_NAME))
        viewModel.action.invoke(SignUpUiAction.Email(USER_EMAIL))
        viewModel.action.invoke(SignUpUiAction.Password(PASSWORD))
        viewModel.action.invoke(SignUpUiAction.ConfirmPassword(PASSWORD))
        viewModel.action.invoke(SignUpUiAction.ToggleTermsAndCondition)
        viewModel.action.invoke(SignUpUiAction.SignUp)

        assertEquals(
            true,viewModel.uiState.value.isInputValid
        )

        assertEquals(
            INPUT_VALID,((viewModel.uiState.value.uiText) as UiText.DynamicString).value
        )

        collectJob.cancel()
    }

}