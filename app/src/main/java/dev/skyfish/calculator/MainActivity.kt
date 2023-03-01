package dev.skyfish.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.skyfish.calculator.presentation.CalculatorScreen
import dev.skyfish.calculator.presentation.CalculatorState
import dev.skyfish.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                val state = CalculatorState()
                CalculatorScreen(
                    state = state,
                    onButtonPress = {}
                )
            }
        }
    }
}
