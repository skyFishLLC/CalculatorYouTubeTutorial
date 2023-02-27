package dev.skyfish.calculator.domain

data class ButtonData(
    val text: String,
    val onPress: () -> Unit
)
