package com.example.composesignup.feature.welcome.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesignup.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

private const val Tag = "IntroScreenViewModel"
class IntroScreenVIewModel:ViewModel() {

    private val _uiState = MutableStateFlow(IntroUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getIntroItems()
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
    val data: List<IntroItem> = emptyList()
)
data class IntroItem(
    val id:String = UUID.randomUUID().toString(),
    val image:Int = R.drawable.pixelcut_export,
    val title:String,
    val description:String
)