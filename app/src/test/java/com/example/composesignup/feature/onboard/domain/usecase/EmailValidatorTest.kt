package com.example.composesignup.feature.onboard.domain.usecase


import com.example.composesignup.core.utils.TextFieldException
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class EmailValidatorTest{

    private lateinit var nameUseCase:UserNameValidatorUseCase
    private lateinit var emailUseCase:EmailValidatorUseCase
    private lateinit var passwordUseCase: PasswordValidationUseCase
    private lateinit var useCase: InputFormUseCase
    private var validEmail = "rjjeninjoseph@gmail.com"
    private var emptyEmail = ""
    private var invalidEmail = "jeninjosephgmail.com"
    private var validUserName = "Jenin Joseph"
    private var invalidUserName  = ""

    @Before
    fun setUp(){
        nameUseCase = UserNameValidatorUseCase()
        emailUseCase = EmailValidatorUseCase()
        useCase = InputFormUseCase(nameUseCase,emailUseCase,passwordUseCase)
    }

    @Test
    fun validate_ifUserName_isNotEmpty() = runTest{
        assertThat(useCase.userNameUseCase.invoke(validUserName)).isEqualTo(FormValidation(success = true))
    }

    @Test
    fun invalidate_ifUserName_isEmpty() = runTest{
        val result = useCase.userNameUseCase.invoke(invalidUserName)
        assertThat(result.exception).isInstanceOf(TextFieldException::class.java)
    }

    @Test
    fun validate_ifUserEmail_isValid() = runTest{
        val result = useCase.userEmailUseCase.invoke(validEmail)
        assertThat(result.success).isTrue()
    }

    @Test
    fun invalidate_ifUserEmail_isEmpty() = runTest {
        val result = useCase.userEmailUseCase.invoke(emptyEmail)
        assertThat(result.exception).isInstanceOf(TextFieldException::class.java)
    }
    @Test
    fun invalidate_ifUserEmail_isInValid() = runTest{
        val result = useCase.userEmailUseCase.invoke(invalidEmail)
        assertThat(result.exception).isInstanceOf(TextFieldException::class.java)
    }
}