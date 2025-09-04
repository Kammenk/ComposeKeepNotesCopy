package com.example.composekeepnotescopy

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val contentDescription: String
) {
    NOTES(
        route = "notes",
        label = "Notes",
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Default.Home,
        contentDescription = "Notes"
    ),
    REMINDERS(
        route = "reminders",
        label = "Reminders",
        selectedIcon = Icons.Default.Notifications,
        unselectedIcon = Icons.Default.Notifications,
        contentDescription = "Reminders"
    ),
    LABELS(
        route = "labels",
        label = "Labels",
        selectedIcon = Icons.Default.Star,
        unselectedIcon = Icons.Default.Star,
        contentDescription = "Labels"
    ),
    EDIT_LABEL(
        route = "edit_label",
        label = "Edit Label",
        selectedIcon = Icons.Default.Star,
        unselectedIcon = Icons.Default.Star,
        contentDescription = "Edit Label"
    ),
    ARCHIVE(
        route = "archive",
        label = "Archive",
        selectedIcon = Icons.Default.Star,
        unselectedIcon = Icons.Default.Star,
        contentDescription = "Archive"
    ),
    TRASH(
        route = "trash",
        label = "Trash",
        selectedIcon = Icons.Default.Star,
        unselectedIcon = Icons.Default.Star,
        contentDescription = "Trash"
    ),
    SETTINGS(
        route = "settings",
        label = "Settings",
        selectedIcon = Icons.Default.Star,
        unselectedIcon = Icons.Default.Star,
        contentDescription = "Settings"
    )


}