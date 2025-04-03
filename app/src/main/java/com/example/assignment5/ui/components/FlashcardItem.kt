package com.example.assignment5.ui.components

import androidx.compose.runtime.*
import com.example.assignment5.data.model.Flashcard

@Composable
fun FlashcardItem(flashcard: Flashcard, onFlip: () -> Unit) {
    var isFlipped by remember { mutableStateOf(flashcard.isFlipped) }

    FlashcardFlipAnimation(
        flashcard = flashcard,
        isFlipped = isFlipped,
        onClick = {
            isFlipped = !isFlipped
            onFlip()
        }
    )
}