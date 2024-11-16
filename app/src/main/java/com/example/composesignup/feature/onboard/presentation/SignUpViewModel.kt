package com.example.composesignup.feature.onboard.presentation

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val Tag = "SignUpViewModel"
class SignUpViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState = _uiState.asStateFlow()
    /**
     * Storing User Inputs in Ui State is Avoided as
     * reactive streams causes delays in updating the TextField.
     */
    var userName by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set

    val action:(SignUpUiAction)->Unit
    init {
        setValidationMessages()
        action = {
            onUiAction(it)
        }

    }
    private fun onUiAction(action: SignUpUiAction){
        when(action){
            is SignUpUiAction.SignUp->{

            }
            is SignUpUiAction.Email->{
                email = action.email
                Log.d(Tag, "onUiAction() called with: email = ${action.email}")
            }
            is SignUpUiAction.UserName->{
                userName = action.name
                Log.d(Tag, "onUiAction() called with: name = ${action.name}")
            }
            is SignUpUiAction.Password->{
                password=action.password
                    if (isPasswordValid(password)){
                        _uiState.update {
                            it.copy(
                                isPasswordSizeValid = true,
                                isCredentialsValid = true
                            )
                        }
                    }else{
                        _uiState.update {
                            it.copy(
                                isPasswordSizeValid = false,
                                isCredentialsValid = false
                            )
                        }
                    }
                Log.d(Tag, "onUiAction() called with: password = ${action.password}")
            }
            is SignUpUiAction.ConfirmPassword->{
                confirmPassword = action.password
                Log.d(Tag, "onUiAction() called with: confirmPassword = ${action.password}")
            }
        }
    }
    private fun toggleHelperTextColor(id:String="1"){
        val data = uiState.value.validationMessages.listIterator()
        while (data.hasNext()){
            val currentItem = data.next()
            if (currentItem.id==id && !currentItem.isInputValid){
                data.set(currentItem.copy(isInputValid = true))
            }
        }
    }

    private fun setValidationStatus(isValid:Boolean){
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isCredentialsValid = isValid
                )
            }
        }
    }
    private fun setValidationMessages(){
        viewModelScope.launch (){
            val tempList = uiState.value.validationMessages
            tempList.addAll(getValidationMessages().toMutableStateList())
            _uiState.update {
                it.copy(
                    validationMessages = tempList
                )
            }
        }
    }
    private fun getValidationMessages(): ArrayList<ValidationMessage> {
        return arrayListOf(
            ValidationMessage(message = "At Least 8 Characters", isInputValid = false, id = "1"),
            ValidationMessage(message = "At Least 1 number", isInputValid = false, id = "2"),
            ValidationMessage(message = "Both Upper Case and Lower Case Letters", isInputValid = false, id = "3")
        )
    }
    private fun isValidString(input: String): Boolean {
        val pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$"
        val regex = Regex(pattern)
        return regex.matches(input)
    }
    private fun setInputValidation(){
        viewModelScope.launch {
            val data = uiState.value.validationMessages.listIterator()
            while (data.hasNext()){
                val currentItem = data.next()
                if(currentItem.id>"1"){
                    data.set(currentItem.copy(isInputValid = true))
                }
            }
        }
    }
    private fun isPasswordValid(password:String):Boolean{
        val pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$".toRegex()
        return password.matches(pattern)
    }


}
data class SignUpUiState(
    val isCredentialsValid:Boolean = false,
    val validationMessages:SnapshotStateList<ValidationMessage> = SnapshotStateList(),
    val isPasswordTyping:Boolean = false,
    val isPasswordSizeValid:Boolean = false
)
sealed class SignUpUiAction{
    data object SignUp:SignUpUiAction()
    data class UserName(val name:String):SignUpUiAction()
    data class Email(val email:String):SignUpUiAction()
    data class Password(val password:String):SignUpUiAction()
    data class ConfirmPassword(val password:String):SignUpUiAction()
}
@Immutable
data class ValidationMessage(
    val id:String = "0",
    val message:String="",
    val isInputValid:Boolean
)