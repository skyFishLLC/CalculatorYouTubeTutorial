package dev.skyfish.calculator.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.skyfish.calculator.domain.MathOperation
import dev.skyfish.calculator.ui.theme.CalculatorTheme
import dev.skyfish.calculator.ui.theme.screenBackground

@Composable
fun DisplayScreen(state: CalculatorState, modifier: Modifier = Modifier){
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(15.dp))
            .background(screenBackground)
            .padding(6.dp)
    ) {
        ScreenRow(
            fontSize = 34,
            text = state.currentEquation,
            modifier = modifier
        )
        ScreenRow(
            fontSize = 60,
            text = state.displayNumber,
            modifier = modifier
        )
    }
}

@Composable
private fun ScreenRow(fontSize: Int, text: String, modifier: Modifier = Modifier){
    Row(modifier = modifier){
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            fontSize = fontSize.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.width(4.dp))
    }
}

@Preview
@Composable
fun DisplayScreenPreview(){
    CalculatorTheme {
        var state = CalculatorState()
        state = state.copy(
            displayNumber = "3.14",
            number1 = "3",
            number2 = "3.14",
            operation = MathOperation.Multiplication,
            currentEquation = "3 x 3.14"
        )
        DisplayScreen(state = state)
    }
}

