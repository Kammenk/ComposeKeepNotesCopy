package com.example.composekeepnotescopy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.composekeepnotescopy.ui.theme.ComposeKeepNotesCopyTheme
import com.example.composekeepnotescopy.presentation.home.NotesScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeKeepNotesCopyTheme {
                val noLabels = false
                val navController = rememberNavController()
                val startScreen = Screen.NOTES
                var selectedScreen by rememberSaveable { mutableIntStateOf(startScreen.ordinal) }

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerState = drawerState,drawerContent = {
                    ModalDrawerSheet {
                        Text("Google Keep", modifier = Modifier.padding(start = 16.dp, bottom = 16.dp))
                        Spacer(modifier = Modifier)
                        Screen.entries.forEachIndexed { index, screen ->
                            NavigationDrawerItem(
                                modifier = Modifier.padding(start = 12.dp, end = 16.dp),
                                label = { Text(screen.label, fontWeight = if (screen != Screen.CREATE_LABEL) {
                                    FontWeight.SemiBold
                                } else {
                                    FontWeight.Normal
                                }) },
                                selected = selectedScreen == index,
                                onClick = {
                                    navController.navigate(route = screen.route)
                                    selectedScreen = index
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
                                HorizontalDivider(modifier = Modifier.padding(start = 28.dp, end = 28.dp))
                            }
                        }
                    }
                }) {
                    Scaffold(modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopAppBar(
                                title = { },
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
                            FloatingActionButton(
                                onClick = {

                                },
                                containerColor = Color.White,
                                modifier = Modifier
                                    .border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
                                elevation = FloatingActionButtonDefaults.elevation(0.dp)
                            ) {
                                Icon(Icons.Filled.Add, "Floating action button.")
                            }
                        },) { innerPadding ->
                        NotesScreen(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}