package com.example.animationcounter

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Row


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedCounter(
    name: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium
) {
    var oldCount by remember {
        mutableStateOf(name)
    }
    SideEffect {
        oldCount = name
    }
    Row(modifier = modifier) {
        val countString = name
        val oldCountString = oldCount
        for(i in countString.indices) {
            val oldChar = oldCountString.getOrNull(i)
            val newChar = countString[i]
            val char = if(oldChar == newChar) {
                oldCountString[i]
            } else {
                countString[i]
            }
            AnimatedContent(
                targetState = char,
                transitionSpec = {
                    slideInVertically { it } with slideOutVertically { -it }
                }
            ) { char ->
                Text(
                    text = char.toString(),
                    style = style,
                    softWrap = false
                )
            }
        }
    }
}