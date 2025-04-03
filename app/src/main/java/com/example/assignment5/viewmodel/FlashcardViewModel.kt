package com.example.assignment5.viewmodel

import androidx.lifecycle.ViewModel
import com.example.assignment5.data.model.Flashcard
import com.example.assignment5.data.repository.FlashcardRepository
import androidx.compose.runtime.mutableStateListOf

class FlashcardViewModel : ViewModel() {

    private val repository = FlashcardRepository()
    private val _flashcards = mutableStateListOf<Flashcard>().apply { addAll(repository.getFlashcards()) }

    val flashcards: List<Flashcard> get() = _flashcards

    private var currentIndex = 0

    fun flipCard(index: Int) {
        _flashcards[index] = _flashcards[index].copy(isFlipped = !_flashcards[index].isFlipped)
    }

    fun nextCard() {
        if (currentIndex < _flashcards.size - 1) {
            currentIndex++
        }
    }

    fun previousCard() {
        if (currentIndex > 0) {
            currentIndex--
        }
    }

    fun getCurrentCard(): Flashcard? {
        return _flashcards.getOrNull(currentIndex)
    }
}