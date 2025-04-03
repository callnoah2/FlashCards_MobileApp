package com.example.assignment5.data.model

data class Flashcard(
    val id: Int,
    val frontText: String,
    val backText: String,
    var isFlipped: Boolean = false
)