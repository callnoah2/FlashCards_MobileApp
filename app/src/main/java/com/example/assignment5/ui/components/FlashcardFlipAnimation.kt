package com.example.assignment5.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment5.data.model.Flashcard

@Composable
fun FlashcardFlipAnimation(
    flashcard: Flashcard,
    isFlipped: Boolean,
    onFlip: () -> Unit,
    onSwipeLeft: () -> Unit,
    onSwipeRight: () -> Unit
) {
    var rotation by remember { mutableStateOf(0f) } // Rotation angle (for flip)
    var offset by remember { mutableStateOf(0f) } // Translation offset (for swipe)
    var isDragging by remember { mutableStateOf(false) } // Track if dragging

    val rotationAnim by animateFloatAsState(
        targetValue = rotation,
        animationSpec = tween(durationMillis = 300),
        label = "Flip Animation"
    )

    // Handle gestures (drag and flip)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    if (isFlipped) {
                        // Translate when flipped
                        offset = (offset + dragAmount).coerceIn(-500f, 500f)
                        isDragging = true
                    } else {
                        // Rotate when not flipped
                        rotation = (rotation + dragAmount).coerceIn(-90f, 90f)
                        if (rotation <= -90f) {
                            onFlip()
                            rotation = -90f // Lock to -90 when flipped
                        } else if (rotation >= 90f) {
                            onFlip()
                            rotation = 90f // Lock to 90 when flipped
                        }
                    }
                }
            }
            .offset(x = offset.dp) // Apply the translation offset
    ) {
        Card(
            modifier = Modifier
                .width(300.dp)
                .height(200.dp)
                .graphicsLayer {
                    rotationY = rotationAnim // Rotate the card
                    cameraDistance = 12 * density
                }
                .clickable {
                    onFlip() // Flip on click
                }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Display front/back text based on the rotation
                Text(
                    text = if (rotationAnim < 0f) flashcard.backText else flashcard.frontText,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    // After drag ends, reset position or swap cards if necessary
    LaunchedEffect(rotationAnim) {
        if (isDragging) {
            if (rotation < -90f) {
                onSwipeLeft() // Swipe left (next card)
            } else if (rotation > 90f) {
                onSwipeRight() // Swipe right (previous card)
            } else {
                // Reset card to original position
                rotation = 0f
                offset = 0f
            }
            isDragging = false
        }
    }
}