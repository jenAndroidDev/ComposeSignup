package com.example.composesignup.feature.welcome.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesignup.R
import com.example.composesignup.core.sessionManager.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

private const val Tag = "IntroScreenViewModel"
@HiltViewModel
class IntroScreenViewModel @Inject constructor(
    private val sessionManager: SessionManager
):ViewModel() {

    //get items from
    private val _uiState = MutableStateFlow(IntroUiState())
    val uiState = _uiState.asStateFlow()

    val action:(IntroScreenUiAction)->Unit

    init {
        getIntroItems()
        action = {
            onUiAction(it)
        }
    }
    private fun onUiAction(action: IntroScreenUiAction){
        when(action){
            is IntroScreenUiAction.SetWelcomeScreenStatus->{
                viewModelScope.launch(Dispatchers.IO) {
                    sessionManager.saveWelcomeScreenStatus(true)
                }
                Log.d(Tag, "onUiAction() called with: action = $action")
            }
            is IntroScreenUiAction.IntroScreenSkipped->{
                viewModelScope.launch(Dispatchers.IO) {
                    sessionManager.saveWelcomeScreenStatus(true)
                }
                Log.d(Tag, "onUiAction() called with: action = $action")
            }
            is IntroScreenUiAction.IntroScreenSlideCompleted->{
                viewModelScope.launch(Dispatchers.IO) {
                    sessionManager.saveWelcomeScreenStatus(true)
                    _uiState.update {
                        it.copy(
                            isWelcomeSlideCompleted = true
                        )
                    }
                }
                Log.d(Tag, "onUiAction() called with: action = $action")
            }
        }
    }
    private fun getIntroItems(){
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(Tag, "getIntroItems() called")
            val tempList = uiState.value.data.toMutableList()
            val items = arrayListOf(
                IntroItem(UUID.randomUUID().toString(),
                    R.drawable.pixelcut_export,
                    "Team Up For Success",
                      "Get ready to unleash your witness the power of teamwork" +
                              "as we embark on this extraordinary project"
                    ),
                IntroItem(UUID.randomUUID().toString(),
                    R.drawable.pixelcut_export,
                    "Team Up For Success",
                    "Get ready to unleash your witness the power of teamwork" +
                            "as we embark on this extraordinary project"
                ),
                IntroItem(UUID.randomUUID().toString(),
                    R.drawable.pixelcut_export,
                    "Team Up For Success",
                    "Get ready to unleash your witness the power of teamwork" +
                            "as we embark on this extraordinary project"
                ))
            tempList.addAll(
                items
            )
            _uiState.update {
                it.copy(
                    data = tempList
                )
            }
        }
    }
}
data class IntroUiState(
    val data: List<IntroItem> = emptyList(),
    val isWelcomeSlideCompleted:Boolean = false
)
data class IntroItem(
    val id:String = UUID.randomUUID().toString(),
    val image:Int = R.drawable.pixelcut_export,
    val title:String,
    val description:String
)
sealed class IntroScreenUiAction{
    data object SetWelcomeScreenStatus:IntroScreenUiAction()
    data object IntroScreenSkipped:IntroScreenUiAction()
    data object IntroScreenSlideCompleted:IntroScreenUiAction()
}