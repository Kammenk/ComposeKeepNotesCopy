package com.example.composekeepnotescopy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composekeepnotescopy.presentation.archive.ArchiveScreen
import com.example.composekeepnotescopy.presentation.create_label.CreateLabelScreen
import com.example.composekeepnotescopy.ui.theme.ComposeKeepNotesCopyTheme
import com.example.composekeepnotescopy.presentation.notes.NotesScreen
import com.example.composekeepnotescopy.presentation.reminders.RemindersScreen
import com.example.composekeepnotescopy.presentation.settings.SettingsScreen
import com.example.composekeepnotescopy.presentation.trash.TrashScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val mainActivityViewModel: MainActivityViewModel = hiltViewModel()
            val state by mainActivityViewModel.state.collectAsStateWithLifecycle()

            ComposeKeepNotesCopyTheme(
                darkTheme = when(state.isDarkTheme) {
                    ThemeColor.LIGHT.text -> false
                    ThemeColor.DARK.text -> true
                    else -> isSystemInDarkTheme()
                }
            ) {
                val noLabels = false
                val navController = rememberNavController()
                val startScreen = Screen.NOTES
                var selectedScreen by rememberSaveable { mutableIntStateOf(startScreen.ordinal) }

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerState = drawerState, drawerContent = {
                        ModalDrawerSheet {
                            Text(
                                "Google Keep",
                                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                            )
                            Spacer(modifier = Modifier)
                            Screen.entries.forEachIndexed { index, screen ->
                                NavigationDrawerItem(
                                    modifier = Modifier.padding(start = 12.dp, end = 16.dp),
                                    label = {
                                        Text(
                                            screen.label,
                                            fontWeight = if (screen != Screen.CREATE_LABEL) {
                                                FontWeight.SemiBold
                                            } else {
                                                FontWeight.Normal
                                            }
                                        )
                                    },
                                    selected = selectedScreen == index,
                                    onClick = {
                                        navController.navigate(route = screen.route)
                                        selectedScreen = index

                                        scope.launch {
                                            drawerState.close()
                                        }
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = screen.selectedIcon,
                                            contentDescription = screen.contentDescription
                                        )
                                    },
                                    colors = NavigationDrawerItemDefaults.colors(
                                        selectedContainerColor = Color.DarkGray,
                                        selectedTextColor = Color.White,
                                        selectedIconColor = Color.White
                                    )
                                )
                                if (noLabels && screen == Screen.REMINDERS) {
                                    HorizontalDivider(
                                        modifier = Modifier.padding(
                                            start = 28.dp,
                                            end = 28.dp
                                        )
                                    )
                                }
                            }
                        }
                    }) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopAppBar(
                                title = {
                                    if (selectedScreen != Screen.NOTES.ordinal) {
                                        Text(Screen.entries[selectedScreen].label)
                                    } else {
                                    }
                                },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        scope.launch {
                                            if (drawerState.isClosed) {
                                                drawerState.open()
                                            } else {
                                                drawerState.close()
                                            }
                                        }
                                    }) {
                                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                                    }
                                }
                            )
                        },
                        floatingActionButton = {
                            ExtendedFloatingActionButton(
                                onClick = {
                                    //isDarkTheme.value = !isDarkTheme.value
                                },
                                containerColor = Color.Black,
                                modifier = Modifier
                                    .border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
                                contentColor = Color.White
                            ) {
                                Icon(Icons.Filled.Add, "Floating action button.")
                            }
                        },
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = startScreen.route
                        ) {
                            Screen.entries.forEach { screen ->
                                composable(screen.route) {
                                    when (screen) {
                                        Screen.NOTES -> NotesScreen(
                                            modifier = Modifier.padding(
                                                innerPadding
                                            )
                                        )

                                        Screen.REMINDERS -> RemindersScreen(
                                            modifier = Modifier.padding(
                                                innerPadding
                                            )
                                        )

                                        Screen.CREATE_LABEL -> CreateLabelScreen(
                                            modifier = Modifier.padding(
                                                innerPadding
                                            )
                                        )

                                        Screen.ARCHIVE -> ArchiveScreen(
                                            modifier = Modifier.padding(
                                                innerPadding
                                            )
                                        )

                                        Screen.TRASH -> TrashScreen(
                                            modifier = Modifier.padding(
                                                innerPadding
                                            )
                                        )

                                        Screen.SETTINGS -> SettingsScreen(
                                            modifier = Modifier.padding(
                                                innerPadding
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}