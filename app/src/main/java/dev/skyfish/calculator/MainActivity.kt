package dev.skyfish.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.skyfish.calculator.presentation.CalculatorScreen
import dev.skyfish.calculator.presentation.CalculatorViewModel
import dev.skyfish.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state = viewModel.state
                CalculatorScreen(
                    state = state,
                    onButtonPress = viewModel::onButton
                )
            }
        }
    }
}
