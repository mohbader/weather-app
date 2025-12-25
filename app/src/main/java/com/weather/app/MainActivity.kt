package com.weather.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.weather.app.navigation.MainNavGraph
import com.weather.navigation.MainNav
import com.weather.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.isCitySavedState.collect { isCitySaved ->
                isCitySaved ?: return@collect
                val startDestination =
                    if (isCitySaved) MainNav.MainRout.Home else MainNav.MainRout.Search
                setContent(startDestination)
            }
        }
    }

    private fun setContent(startDestination: MainNav.MainRout) {
        setContent {
            WeatherAppTheme {
                MainNavGraph(startDestination = startDestination)
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
    WeatherAppTheme {
        Greeting("Android")
    }
}