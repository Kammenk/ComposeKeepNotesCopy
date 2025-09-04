package com.example.composekeepnotescopy.presentation.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composekeepnotescopy.util.DateUtils

@Composable
internal fun HistoryScreen(modifier: Modifier) {
    val historyScreenViewModel: HistoryScreenViewModel = hiltViewModel()
    val state by historyScreenViewModel.state.collectAsStateWithLifecycle()

    HistoryScreenContents(state = state, modifier)
}

@Composable
fun HistoryScreenContents(state: HistoryScreenUiState, modifier: Modifier) {

    //Loading

    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        LazyColumn(modifier = Modifier) {
            state.toDos.forEach { todo ->
                stickyHeader {
                    StickyHeader(
                        text = if (todo.key == DateUtils.getTodaysDate()) "Today" else todo.key.toString(),
                    )
                    HorizontalDivider(
                        Modifier.padding(top = 4.dp, bottom = 6.dp),
                        DividerDefaults.Thickness,
                        DividerDefaults.color
                    )
                }

                items(todo.value) { todo ->
                    ToDoHistoryItem(text = todo.title, isCompleted = todo.isCompleted)
                }
            }
        }
    }
}

@Composable
fun StickyHeader(text: String) {
    Text(
        text = text, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 6.dp), fontSize = 20.sp
    )
}

@Composable
fun ToDoHistoryItem(text: String, isCompleted: Boolean = false) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = "- ".plus(text),
                modifier = Modifier,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            if (isCompleted) {
                Icon(Icons.Default.CheckCircle, "Task Done", tint = Color.Green)
            } else {
                Icon(Icons.Default.Clear, "Task Failed", tint = Color.Red)

            }
    }
}

@Preview
@Composable
fun HistoryScreenPreview() {
    HistoryScreenContents(
        modifier = Modifier, state = HistoryScreenUiState(
            isLoading = false, sortedMapOf(

            )
        )
    )
}