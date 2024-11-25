package com.example.composesignup


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composesignup.core.navigation.rememberComposeSignUpState
import com.example.composesignup.core.sessionManager.SessionManager
import com.example.composesignup.feature.foryou.navigation.FOR_YOU_ROUTE
import com.example.composesignup.feature.onboard.presentation.LoginScreen
import com.example.composesignup.feature.onboard.presentation.LoginUiAction
import com.example.composesignup.feature.onboard.presentation.OnboardScreen
import com.example.composesignup.feature.onboard.presentation.SignUpScreen
import com.example.composesignup.feature.welcome.navigation.WELCOME_ROUTE
import com.example.composesignup.ui.theme.ComposeSignupTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

private const val Tag = "MainActivity"
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sessionManager: SessionManager
    private val viewModel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            ComposeSignupTheme {
                val isWelcomeScreenShown = runBlocking {
                    sessionManager.isWelcomeScreenShown().firstOrNull()?:false
                }
               val startDestination = if (!isWelcomeScreenShown){
                   WELCOME_ROUTE
                }else FOR_YOU_ROUTE

//                val appState = rememberComposeSignUpState()
//                ComposeSignUpApp(appState = appState, startDestination = startDestination)
                //SignUpScreen()
                //LoginScreen(modifier = Modifier)
                OnboardScreen(modifier = Modifier)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeSignupTheme {
        Greeting("Android")
    }
}