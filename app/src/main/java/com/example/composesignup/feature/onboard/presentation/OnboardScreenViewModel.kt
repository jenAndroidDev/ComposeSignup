package com.example.composesignup.feature.onboard.presentation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesignup.core.sessionManager.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

private const val Tag  = "OnboardScreenViewModel"
@HiltViewModel
class OnboardScreenViewModel @Inject constructor(
    private val sessionManager: SessionManager
):ViewModel() {

    private val _uiState= MutableStateFlow(OnboardUiState())
    val uiState = _uiState.asStateFlow()

    val action:(OnboardScreenUiAction)->Unit

    init {

        action ={
            onUiAction(it)
        }
    }
    private fun onUiAction(action: OnboardScreenUiAction){
        when(action){
            is OnboardScreenUiAction.SetWelcomeScreenStatus->{
                viewModelScope.launch(Dispatchers.IO) {
                    sessionManager.setWelcomeScreenStatus(
                        isShown = true
                    )
                }
                Timber.tag(Tag).d("")
            }
        }
    }

}
sealed class OnboardScreenUiAction{
    data object SetWelcomeScreenStatus:OnboardScreenUiAction()
}
data class OnboardUiState(
    val isOnboardShown:Boolean = false
)
data class OnboardTabItem(
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
    val description:String
)