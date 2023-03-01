package dev.skyfish.calculator.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.skyfish.calculator.domain.ButtonAction
import dev.skyfish.calculator.domain.MathOperation

class CalculatorViewModel: ViewModel() {
    
    var state by mutableStateOf(CalculatorState())
    
    fun onButton(action: ButtonAction){
        when(action){
            is ButtonAction.Number -> writeNumber(action.number)
            is ButtonAction.Operation -> writeOperator(action.operation)
            is ButtonAction.Equals -> calculate()
            is ButtonAction.Decimal -> addDecimal()
            is ButtonAction.Negation -> negate()
            is ButtonAction.Clear -> clearScreen()
            is ButtonAction.Backspace -> backspace()
        }
    }

    private fun writeNumber(newNumber: Int) {
        val displayNumber: String
        val isFirstNumber = state.operation == null

        if(isFirstNumber){
            displayNumber = state.number1 + newNumber.toString()
            state = state.copy(
                displayNumber = displayNumber,
                number1 = displayNumber,
                currentEquation = displayNumber
            )
            return
        } else {
            displayNumber = state.number2 + newNumber.toString()
            state = state.copy(
                displayNumber = displayNumber,
                number2 = displayNumber,
                currentEquation =  state.currentEquation + newNumber.toString()
            )
        }
    }

    private fun writeOperator(operation: MathOperation) {
        val isFirstOperator = state.operation == null
        val shouldOverwriteOperator = state.operation != null && state.number2 == ""
        val shouldUseLastResult = state.number1 == "" && state.displayNumber != ""
        val alreadyHasOperatorNeedToCalculate = state.number1 != "" && state.number2 != ""

        if(shouldUseLastResult){
            state = state.copy(
                operation = operation,
                number1 = state.displayNumber
            )
        }else if(alreadyHasOperatorNeedToCalculate){
            calculate()
            state = state.copy(
                operation = operation,
                number1 = state.displayNumber
            )
        }else if(isFirstOperator || shouldOverwriteOperator){
            state = state.copy(
                operation = operation
            )
        }
        state = state.copy(
            currentEquation = writePartialEquation()
        )

    }

    private fun addDecimal() {
        TODO("Not yet implemented")
    }

    private fun negate() {
        TODO("Not yet implemented")
    }

    private fun clearScreen() {
        TODO("Not yet implemented")
    }

    private fun backspace() {
        TODO("Not yet implemented")
    }

    private fun calculate() {
        val operand1 = state.number1.toDoubleOrNull()
        val operand2 = state.number2.toDoubleOrNull()

        if(operand1 != null && operand2 != null){
            val result: Double
            val equation: String
            when(state.operation){
                is MathOperation.Addition -> {
                    result = operand1 + operand2
                    equation = writeFullEquation(result)
                }
                is MathOperation.Subtraction -> {
                    result = operand1 - operand2
                    equation = writeFullEquation(result)
                }
                is MathOperation.Multiplication -> {
                    result = operand1 * operand2
                    equation = writeFullEquation(result)
                }
                is MathOperation.Division -> {
                    result = operand1 / operand2
                    equation = writeFullEquation(result)
                }
                else -> return
            }
            state = state.copy(
                displayNumber = dropZerosAfterDecimal(result),
                currentEquation = "",
                operation = null,
                number1 = "",
                number2 = "",
                equations = state.equations + equation
            )
        }
    }



    private fun writePartialEquation(): String{
        val equation = if(state.operation != null){
            state.number1 + " " + state.operation!!.symbol + " " + state.number2
        }else{
            state.number1
        }
        return equation
    }

    private fun writeFullEquation(result: Double): String {
        val resultString = dropZerosAfterDecimal(result)
        val equation = if(state.operation != null){
            state.number1 + " " + state.operation!!.symbol +  " " + state.number2 + " = " + resultString
        }else{
            state.number1
        }
        return equation
    }

    private fun dropZerosAfterDecimal(number: Double): String{
        return if(isWholeNumber(number)){
            number.format(0)
        }else{
            return number.toString()
        }
    }

    private fun Double.format(scale: Int) = "%.${scale}f".format(this)

    private fun isWholeNumber(number: Double): Boolean{
        if(number % 1 == 0.0){
            return true
        }
        return false
    }

}








