package com.example.assignment5.data.repository

import com.example.assignment5.data.model.Flashcard

class FlashcardRepository {

    fun getFlashcards(): List<Flashcard> {
        return listOf(
            Flashcard(1, "What is the capital of France?", "Paris"),
            Flashcard(2, "What is 2 + 2?", "4"),
            Flashcard(3, "Who wrote '1984'?", "George Orwell"),
            Flashcard(4, "What is the square root of 16?", "4"),
            Flashcard(5, "What is the largest planet?", "Jupiter"),
            Flashcard(6, "What is H2O?", "Water"),
            Flashcard(7, "Who painted the Mona Lisa?", "Leonardo da Vinci"),
            Flashcard(8, "What is the boiling point of water?", "100°C"),
            Flashcard(9, "What is the capital of Japan?", "Tokyo"),
            Flashcard(10, "What is 10 / 2?", "5"),
            Flashcard(11, "Who discovered gravity?", "Isaac Newton"),
            Flashcard(12, "What is the speed of light?", "299,792,458 m/s"),
            Flashcard(13, "What is the chemical symbol for gold?", "Au"),
            Flashcard(14, "Who developed the theory of relativity?", "Albert Einstein"),
            Flashcard(15, "What is the smallest unit of life?", "Cell"),
            Flashcard(16, "What does DNA stand for?", "Deoxyribonucleic Acid"),
            Flashcard(17, "What is the hardest natural substance?", "Diamond"),
            Flashcard(18, "What is the tallest mountain?", "Mount Everest"),
            Flashcard(19, "What is the longest river?", "Nile River"),
            Flashcard(20, "Who was the first president of the USA?", "George Washington")
        )
    }
}