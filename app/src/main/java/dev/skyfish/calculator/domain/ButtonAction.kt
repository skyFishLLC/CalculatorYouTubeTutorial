package dev.skyfish.calculator.domain

sealed class ButtonAction {
    object Clear: ButtonAction()
    object Backspace: ButtonAction()
    object Equals: ButtonAction()
    object Decimal: ButtonAction()
    object Negation: ButtonAction()
    data class Operation(val operation: MathOperation): ButtonAction()
    data class Number(val number: Int): ButtonAction()
}