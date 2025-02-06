package com.example.composesignup.feature.onboard.presentation

import com.example.composesignup.core.testing.util.MainDispatcherRule
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class LoginViewModelTest{

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp(){
        viewModel = LoginViewModel()
    }

    @Test
    fun uiState_when_uiError_isShow() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        assertEquals(
            null,viewModel.uiState.value.uiText
        )

        assertEquals(
            null,viewModel.uiState.value.exception
        )
        collectJob.cancel()

    }
}