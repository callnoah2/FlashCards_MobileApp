package com.example.assignment5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assignment5.ui.screens.FlashcardScreen
import com.example.assignment5.viewmodel.FlashcardViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                val viewModel: FlashcardViewModel = viewModel()
                FlashcardScreen(viewModel = viewModel)
        }
    }
}