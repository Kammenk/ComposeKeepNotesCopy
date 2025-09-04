package com.example.composekeepnotescopy

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Label
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Label
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
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
        selectedIcon = Icons.Outlined.Lightbulb,
        unselectedIcon = Icons.Outlined.Lightbulb,
        contentDescription = "Notes"
    ),
    REMINDERS(
        route = "reminders",
        label = "Reminders",
        selectedIcon = Icons.Outlined.Notifications,
        unselectedIcon = Icons.Outlined.Notifications,
        contentDescription = "Reminders"
    ),
    CREATE_LABEL(
        route = "create_new_label",
        label = "Create new label",
        selectedIcon = Icons.Default.Add,
        unselectedIcon = Icons.Default.Add,
        contentDescription = "Edit Label"
    ),
//    LABELS(
//        route = "labels",
//        label = "Labels",
//        selectedIcon = Icons.Default.Star,
//        unselectedIcon = Icons.Default.Star,
//        contentDescription = "Labels"
//    ),
//    EDIT_LABEL(
//        route = "edit_label",
//        label = "Edit Label",
//        selectedIcon = Icons.AutoMirrored.Outlined.Label,
//        unselectedIcon = Icons.AutoMirrored.Outlined.Label,
//        contentDescription = "Edit Label"
//    ),
    ARCHIVE(
        route = "archive",
        label = "Archive",
        selectedIcon = Icons.Outlined.Archive,
        unselectedIcon = Icons.Outlined.Archive,
        contentDescription = "Archive"
    ),
    TRASH(
        route = "trash",
        label = "Trash",
        selectedIcon = Icons.Outlined.Delete,
        unselectedIcon = Icons.Outlined.Delete,
        contentDescription = "Trash"
    ),
    SETTINGS(
        route = "settings",
        label = "Settings",
        selectedIcon = Icons.Outlined.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        contentDescription = "Settings"
    )


}