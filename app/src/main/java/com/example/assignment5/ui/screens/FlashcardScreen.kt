package com.example.assignment5.ui.screens

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import com.example.assignment5.ui.components.FlashcardItem
import com.example.assignment5.viewmodel.FlashcardViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FlashcardScreen(viewModel: FlashcardViewModel = viewModel()) {
    val flashcards = viewModel.flashcards
    var currentIndex by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LazyRow(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { _, dragAmount ->
                        if (dragAmount < -100) {
                            // Swipe left: Move to the next card if flipped
                            if (flashcards[currentIndex].isFlipped) {
                                viewModel.nextCard()
                                if (currentIndex < flashcards.size - 1) {
                                    currentIndex++
                                }
                            }
                        } else if (dragAmount > 100) {
                            // Swipe right: Go back to the previous card
                            viewModel.previousCard()
                            if (currentIndex > 0) {
                                currentIndex--
                            }
                        }
                    }
                },
            horizontalArrangement = Arrangement.Center
        ) {
            itemsIndexed(flashcards) { index, flashcard ->
                if (index == currentIndex) {
                    FlashcardItem(
                        flashcard = flashcard,
                        onFlip = { viewModel.flipCard(index) }
                    )
                }
            }
        }
    }
}