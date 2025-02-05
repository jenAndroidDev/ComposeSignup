package com.example.composesignup.feature.onboard.domain.usecase

import com.example.composesignup.core.utils.TextFieldException
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class PasswordValidationUseCaseTest{

    private lateinit var nameUseCase:UserNameValidatorUseCase
    private lateinit var emailUseCase:EmailValidatorUseCase
    private lateinit var passwordUseCase: PasswordValidationUseCase
    private lateinit var useCase: InputFormUseCase
    private val validPassword:String = "jeninjos"
    private val inValidPassword:String = ""

    @Before
    fun setUp(){
        nameUseCase = UserNameValidatorUseCase()
        emailUseCase = EmailValidatorUseCase()
        passwordUseCase = PasswordValidationUseCase()
        useCase = InputFormUseCase(nameUseCase,emailUseCase,passwordUseCase)
    }

    @Test
    fun validate_ifPassword_isValid() = runTest {
        val result = useCase.passwordUseCase.invoke(password = validPassword)
        assertThat(result.success)
    }

    @Test
    fun invalidate_ifPassword_isInValid() = runTest {
        val result = useCase.passwordUseCase.invoke(password = inValidPassword)
        assertThat(result.exception).isInstanceOf(TextFieldException::class.java)
    }



}