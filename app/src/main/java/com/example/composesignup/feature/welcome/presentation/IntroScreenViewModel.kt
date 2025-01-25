package com.example.composesignup.feature.welcome.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesignup.R
import com.example.composesignup.core.di.AppDependencies
import com.example.composesignup.core.sessionManager.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
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
            is IntroScreenUiAction.SetWelcomeScreenStatus,
            is IntroScreenUiAction.IntroScreenSkipped->{}
            is IntroScreenUiAction.RefreshInternal->{
                _uiState.update {
                    it.copy(
                        isWelcomeSlideCompleted = false
                    )
                }
            }
            is IntroScreenUiAction.IntroScreenSlideCompleted->{
                AppDependencies.persistentStore?.setWelcomeScreenStatus(true)
                val isWelcomeScreenShown = AppDependencies.persistentStore?.isWelcomeScreenShown
                viewModelScope.launch(Dispatchers.IO) {
                    _uiState.update {
                        it.copy(
                            isWelcomeSlideCompleted = true
                        )
                    }
                }
                Timber.tag(Tag).d("onUiAction() called with: action = " + isWelcomeScreenShown)
            }
        }
    }
    private fun getIntroItems(){
        viewModelScope.launch(Dispatchers.IO) {
            Timber.tag(Tag).d("getIntroItems() called")
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
    data object RefreshInternal:IntroScreenUiAction()
}