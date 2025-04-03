package com.example.assignment5.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment5.data.model.Flashcard

@Composable
fun FlashcardFlipAnimation(
    flashcard: Flashcard,
    isFlipped: Boolean,
    onClick: () -> Unit
) {
    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 400),
        label = "Flip Animation"
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .width(300.dp)  // Fixed width
                .height(200.dp) // Fixed height
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 12 * density
                }
                .clip(RoundedCornerShape(16.dp)), // Ensures shape consistency
            shape = RoundedCornerShape(16.dp),
            onClick = { onClick() },
            colors = androidx.compose.material3.CardDefaults.cardColors(
                containerColor = if (rotation > 90f) Color.LightGray else Color.White
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (rotation > 90f) flashcard.backText else flashcard.frontText,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .graphicsLayer { rotationY = if (rotation > 90f) 180f else 0f }
                        .padding(16.dp)
                )
            }
        }
    }
}