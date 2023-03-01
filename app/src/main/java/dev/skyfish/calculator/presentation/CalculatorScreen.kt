package dev.skyfish.calculator.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.skyfish.calculator.domain.ButtonAction
import dev.skyfish.calculator.domain.MathOperation
import dev.skyfish.calculator.ui.theme.CalculatorTheme
import dev.skyfish.calculator.ui.theme.backgroundGray

@Composable
fun CalculatorScreen(
    state: CalculatorState,
    onButtonPress: (ButtonAction) -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGray)
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.End
    ){
        HistoryDisplay(state = state, modifier = Modifier.weight(1f))
        DisplayScreen(state = state)
        Keypad(onButtonPress = onButtonPress)
    }
}

@Preview
@Composable
fun CalculatorScreenPreview(){
    CalculatorTheme {
        var state = CalculatorState()
        state = state.copy(
            displayNumber = "3.14",
            number1 = "3",
            number2 = "3.14",
            operation = MathOperation.Multiplication,
            currentEquation = "3 x 3.14",
            equations = listOf("7 x 7 = 49", "16 / 2 = 8", "1 + 2 = 3")
        )
        CalculatorScreen(state = state, onButtonPress = { })
    }
}
