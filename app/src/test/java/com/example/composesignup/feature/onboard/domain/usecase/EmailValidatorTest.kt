package com.example.composesignup.feature.onboard.domain.usecase

import androidx.compose.material3.Text
import com.example.composesignup.core.utils.TextFieldException
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class EmailValidatorTest{

    private lateinit var nameUseCase:UserNameValidatorUseCase
    private lateinit var emailUseCase:EmailValidatorUseCase
    private lateinit var useCase: InputFormUseCase
    private var validEmail = "rjjeninjoseph@gmail.com"
    private var invalidEmail = ""
    private var validUserName = "Jenin Joseph"
    private var invalidUserName  = ""

    @Before
    fun setUp(){
        nameUseCase = UserNameValidatorUseCase()
        emailUseCase = EmailValidatorUseCase()
        useCase = InputFormUseCase(
            nameUseCase,emailUseCase
        )
    }

    @Test
    fun validate_ifUserName_isNotEmpty(){
        assertThat(useCase.userNameUseCase.invoke(validUserName)).isEqualTo(FormValidation(success = true))
    }

    @Test
    fun invalidate_ifUserName_isEmpty(){
        val result = useCase.userNameUseCase.invoke(invalidUserName)
        assertThat(result.exception).isInstanceOf(TextFieldException::class.java)
    }

    @Test
    fun validate_ifUserEmail_isValid(){
        val result = useCase.userEmailUseCase.invoke(validEmail)
        assertThat(result.success).isTrue()
    }

    @Test
    fun invalidate_ifUserEmail_isInValid(){
        val result = useCase.userEmailUseCase.invoke(invalidEmail)
        assertThat(result.exception).isInstanceOf(TextFieldException::class.java)
    }
}