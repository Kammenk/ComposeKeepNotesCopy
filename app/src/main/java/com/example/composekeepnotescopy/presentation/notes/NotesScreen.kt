package com.example.composekeepnotescopy.presentation.notes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composekeepnotescopy.data.ToDo
import com.example.composekeepnotescopy.data.ToDoImportance
import com.example.composekeepnotescopy.util.DateUtils

@Composable
internal fun NotesScreen(modifier: Modifier) {
    val notesScreenViewModel: NotesScreenViewModel = hiltViewModel()
    val state by notesScreenViewModel.state.collectAsStateWithLifecycle()

    NotesScreenContent(
        modifier = modifier,
        state = state,
        onCreateTodoClick = notesScreenViewModel::createToDo,
        onDeleteTodoClick = notesScreenViewModel::deleteToDo,
        onCompleteTodoClick = {},
        onDeleteAllTodoClick = notesScreenViewModel::deleteAllToDos
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreenContent(
    modifier: Modifier,
    state: NotesScreenUiState,
    onCreateTodoClick: (ToDo) -> Unit,
    onDeleteTodoClick: (ToDo) -> Unit,
    onCompleteTodoClick: (ToDo) -> Unit,
    onDeleteAllTodoClick: () -> Unit
) {
    //Loading
    val dropDownValues = listOf("Low", "Medium", "High")
    var selectedText by remember { mutableStateOf(dropDownValues[0]) }

    val modalBottomSheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }

    var text by rememberSaveable { mutableStateOf("") }

    var isDropDownExpanded by remember { mutableStateOf(false) }


    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        val padding = innerPadding

        if (state.toDoList.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = Icons.Outlined.Lightbulb,
                    contentDescription = "LightBulb",
                    modifier = Modifier
                    .height(140.dp)
                    .width(140.dp), tint = Color.Magenta)
                Text(
                    text = "Notes you add appear here",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(state.toDoList) {
                    ToDoListItem(it)
                }
            }
        }
    }

    if (isSheetOpen) {
        ModalBottomSheet(
            containerColor = Color.White,
            modifier = Modifier,
            dragHandle = {},
            sheetState = modalBottomSheetState,
            onDismissRequest = { isSheetOpen = false }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 10.dp, end = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Add Your To-Do",
                    modifier = Modifier.padding(bottom = 16.dp),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    TextField(
                        modifier = Modifier
                            .wrapContentWidth()
                            .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                            .widthIn(1.dp),
                        value = text,
                        onValueChange = {
                            text = it
                        },
                        label = { Text("What do you need to-do?") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            focusedLabelColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    Column(
                        modifier = Modifier.fillMaxWidth(.8F)
                    ) {
                        ExposedDropdownMenuBox(
                            expanded = isDropDownExpanded,
                            onExpandedChange = { isDropDownExpanded = !isDropDownExpanded }
                        ) {
                            TextField(
                                modifier = Modifier
                                    .menuAnchor(MenuAnchorType.PrimaryEditable, true)
                                    .border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
                                label = { Text("Importance") },
                                value = selectedText,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropDownExpanded) },
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = Color.Black,
                                    focusedLabelColor = Color.Black,
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                )
                            )

                            ExposedDropdownMenu(
                                modifier = Modifier.border(
                                    1.dp,
                                    Color.Black,
                                    RoundedCornerShape(10.dp)
                                ),
                                shape = RoundedCornerShape(10.dp),
                                containerColor = Color.White,
                                expanded = isDropDownExpanded,
                                onDismissRequest = { isDropDownExpanded = !isDropDownExpanded }) {
                                dropDownValues.forEach {
                                    DropdownMenuItem(
                                        text = { Text(text = it) },
                                        onClick = {
                                            selectedText = it
                                            isDropDownExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = {
                        onCreateTodoClick(
                            ToDo(
                                title = text,
                                importance = when (selectedText) {
                                    "Low" -> ToDoImportance.LOW
                                    "Medium" -> ToDoImportance.MEDIUM
                                    "High" -> ToDoImportance.HIGH
                                    else -> ToDoImportance.LOW
                                }
                            )
                        )
                        isSheetOpen = false
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonColors(
                        contentColor = Color.White,
                        containerColor = Color.Black,
                        disabledContainerColor = Color.Transparent,
                        disabledContentColor = Color.Gray
                    )
                ) {
                    Text(
                        "Add To-Do",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
fun ToDoListItem(toDo: ToDo) {
    Card(
        modifier = Modifier
            .background(Color.White)
            .padding(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(6.dp)

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 10.dp)
                .background(Color.White),
        ) {
            Text(toDo.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)

            when (toDo.importance) {
                ToDoImportance.LOW -> {
                    Icon(Icons.Default.Notifications, "Low Importance", tint = Color.Gray)
                }

                ToDoImportance.MEDIUM -> {
                    Icon(Icons.Default.Info, "Medium Importance", tint = Color.Magenta)
                }

                ToDoImportance.HIGH -> {
                    Icon(Icons.Default.Warning, "High Importance", tint = Color.Red)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListItem() {
    ToDoListItem(
        ToDo(
            id = 0,
            title = "alabala",
            importance = ToDoImportance.LOW,
            creationDate = DateUtils.getTodaysDate(),
            completionDate = null,
            isReoccurring = true,
            isCompleted = false
        )
    )
}

@Preview
@Composable
fun AddTodoActionButton() {
}

@Preview(showBackground = true)
@Composable
fun NotesScreePreview() {
    NotesScreenContent(
        modifier = Modifier,
        state = NotesScreenUiState(
            isLoading = false, toDoList = emptyList()
//                listOf(
//                ToDo(
//                    id = 0,
//                    title = "alabala1",
//                    isReoccurring = true,
//                    isCompleted = false
//                ),
//                ToDo(
//                    id = 0,
//                    title = "alabala2",
//                    isReoccurring = true,
//                    isCompleted = false
//                ),
//                ToDo(
//                    id = 0,
//                    title = "alabala3",
//                    isReoccurring = true,
//                    isCompleted = false
//                )
//            )
        ),
        onCreateTodoClick = {},
        onDeleteTodoClick = {},
        onCompleteTodoClick = {},
        onDeleteAllTodoClick = {}
    )
}