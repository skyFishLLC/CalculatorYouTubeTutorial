package dev.skyfish.calculator.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import dev.skyfish.calculator.domain.ButtonAction
import dev.skyfish.calculator.domain.ButtonData
import dev.skyfish.calculator.domain.MathOperation
import dev.skyfish.calculator.ui.theme.Blue
import dev.skyfish.calculator.ui.theme.CalculatorTheme

@Composable
fun Keypad(onButtonPress: (ButtonAction) -> Unit){

    val clear = {onButtonPress(ButtonAction.Clear)}
    val backspace = {onButtonPress(ButtonAction.Backspace)}
    val negate = {onButtonPress(ButtonAction.Negation)}
    val division = {onButtonPress(ButtonAction.Operation(MathOperation.Division))}
    val multiplication = {onButtonPress(ButtonAction.Operation(MathOperation.Multiplication))}
    val subtraction = {onButtonPress(ButtonAction.Operation(MathOperation.Subtraction))}
    val addition = {onButtonPress(ButtonAction.Operation(MathOperation.Addition))}
    val decimal = {onButtonPress(ButtonAction.Decimal)}
    val equals = {onButtonPress(ButtonAction.Equals)}

    var numbers: List<() -> Unit> = emptyList()
    for(i in 0..9){
        numbers = numbers + {onButtonPress(ButtonAction.Number(i))}
    }

    val row1 = listOf(
        ButtonData(text = "AC", onPress = clear),
        ButtonData(text = "<<", onPress = backspace),
        ButtonData(text = "+/-", onPress = negate),
        ButtonData(text = "/", onPress = division),
    )

    val row2 = listOf(
        ButtonData(text = "7", onPress = numbers[7]),
        ButtonData(text = "8", onPress = numbers[8]),
        ButtonData(text = "9", onPress = numbers[9]),
        ButtonData(text = "x", onPress = multiplication),
    )

    val row3 = listOf(
        ButtonData(text = "4", onPress = numbers[4]),
        ButtonData(text = "5", onPress = numbers[5]),
        ButtonData(text = "6", onPress = numbers[6]),
        ButtonData(text = "-", onPress = subtraction),
    )

    val row4 = listOf(
        ButtonData(text = "1", onPress = numbers[1]),
        ButtonData(text = "2", onPress = numbers[2]),
        ButtonData(text = "3", onPress = numbers[3]),
        ButtonData(text = "+", onPress = addition),
    )


    val row5 = listOf(
        ButtonData(text = "0", onPress = numbers[0]),
        ButtonData(text = ".", onPress = decimal),
        ButtonData(text = "=", onPress = equals),
    )

    Column(){
        BuildRow(row1)
        BuildRow(row2)
        BuildRow(row3)
        BuildRow(row4)
        BuildRow(row5)
    }

}

@Composable
fun BuildRow(row: List<ButtonData>){
    Spacer(modifier = Modifier.height(2.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ){
        for(item in row){
            Spacer(modifier = Modifier.width(2.dp))
            CalculatorButton(
                button = item,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(2.dp))
        }
    }
    Spacer(modifier = Modifier.height(2.dp))
}

@Composable
fun CalculatorButton(button: ButtonData, modifier: Modifier = Modifier){
    var color = Blue
    var textColor = Color.White

    if(button.text == "AC" || button.text == "<<"){
        color = Color.Gray
        textColor = Color.Black
    }
    if(button.text.isDigitsOnly() || button.text == "."){
        color = Color.DarkGray
    }

    Button(
        onClick = button.onPress,
        shape = RoundedCornerShape(15.dp),
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(color)
    ){
        Text(
            text = button.text,
            fontSize = 34.sp,
            color = textColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun KeypadPreview(){
    CalculatorTheme() {
        Keypad {}
    }
}














