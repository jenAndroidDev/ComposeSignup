package com.example.composesignup


import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.composesignup.core.di.AppDependencies
import com.example.composesignup.core.navigation.rememberComposeSignUpState
import com.example.composesignup.core.sessionManager.SessionManager
import com.example.composesignup.feature.foryou.navigation.FOR_YOU_ROUTE
import com.example.composesignup.feature.onboard.navigation.ONBOARD_ROUTE
import com.example.composesignup.feature.welcome.navigation.WELCOME_ROUTE
import com.example.composesignup.ui.theme.ComposeSignupTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

private const val Tag = "MainActivity"
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,Color.TRANSPARENT
            )
        )
        setContent {
            ComposeSignupTheme {
                val isUserLoggedIn = AppDependencies.persistentStore?.isUserLoggedIn?:false
                val signUpStep = AppDependencies.persistentStore?.signUpStep
                val isWelcomeScreenShown = AppDependencies.persistentStore?.isWelcomeScreenShown?:false
                Timber.tag(Tag).d("$isUserLoggedIn,$signUpStep,$isWelcomeScreenShown")
                val startDestination = runBlocking {
                    if (!isUserLoggedIn && !isWelcomeScreenShown) {
                        WELCOME_ROUTE
                    }else if(!isUserLoggedIn && isWelcomeScreenShown){
                        ONBOARD_ROUTE
                    }else{
                        FOR_YOU_ROUTE
                    }
                }
                val appState = rememberComposeSignUpState()
                ComposeSignUpApp(appState = appState, startDestination = startDestination)
            }
        }
    }
}
@Composable
private fun MainScreen(
    uiState: StateFlow<MainActivityUiState>,

){

}
