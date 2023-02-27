package dev.skyfish.calculator.domain

sealed class MathOperation(val symbol: String)  {
    object Addition: MathOperation("+")
    object Subtraction: MathOperation("-")
    object Multiplication: MathOperation("X")
    object Division: MathOperation("/")
}
