package com.example.assignment5.ui.screens

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.example.assignment5.ui.components.FlashcardItem
import com.example.assignment5.viewmodel.FlashcardViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FlashcardScreen(viewModel: FlashcardViewModel = viewModel()) {
    val flashcards = viewModel.flashcards
    var currentIndex by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LazyRow(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            itemsIndexed(flashcards) { index, flashcard ->
                if (index == currentIndex) {
                    FlashcardItem(
                        flashcard = flashcard,
                        onFlip = { viewModel.flipCard(index) },
                        onSwipeLeft = {
                            if (currentIndex < flashcards.size - 1) {
                                currentIndex++
                            }
                        },
                        onSwipeRight = {
                            if (currentIndex > 0) {
                                currentIndex--
                            }
                        }
                    )
                }
            }
        }
    }
}