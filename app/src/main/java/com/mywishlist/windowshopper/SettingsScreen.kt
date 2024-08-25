package com.mywishlist.windowshopper

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    val notificationsEnabled = remember { mutableStateOf(true) }
    val notificationFrequency = remember { mutableStateOf("Daily") }
    val showFrequencyDialog = remember { mutableStateOf(false) }
    val notificationTypes = remember { mutableStateOf("All Notifications") }
    val showTypesDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Settings") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Notification Settings Section
            Spacer(modifier = Modifier.height(16.dp))
            Text("Notification Settings", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp))
            Divider()

            // Enable Notifications
            ListItem(
                text = "Enable Notifications",
                trailing = {
                    Switch(checked = notificationsEnabled.value, onCheckedChange = {
                        notificationsEnabled.value = it
                    })
                },
                onClick = { notificationsEnabled.value = !notificationsEnabled.value }
            )

            // Notification Frequency
            ListItem(
                text = "Notification Frequency: ${notificationFrequency.value}",
                onClick = { showFrequencyDialog.value = true }
            )

            // Notification Types
            ListItem(
                text = "Notification Types: ${notificationTypes.value}",
                onClick = { showTypesDialog.value = true }
            )

            // Dialog for selecting notification frequency
            if (showFrequencyDialog.value) {
                AlertDialog(
                    onDismissRequest = { showFrequencyDialog.value = false },
                    title = { Text("Select Notification Frequency") },
                    text = {
                        Column {
                            listOf("Daily", "Weekly", "Monthly").forEach { frequency ->
                                Text(
                                    text = frequency,
                                    modifier = Modifier
                                        .clickable {
                                            notificationFrequency.value = frequency
                                            showFrequencyDialog.value = false
                                        }
                                        .padding(8.dp)
                                )
                            }
                        }
                    },
                    confirmButton = {}
                )
            }

            // Dialog for selecting notification types
            if (showTypesDialog.value) {
                AlertDialog(
                    onDismissRequest = { showTypesDialog.value = false },
                    title = { Text("Select Notification Types") },
                    text = {
                        Column {
                            listOf("All Notifications", "Only Important").forEach { type ->
                                Text(
                                    text = type,
                                    modifier = Modifier
                                        .clickable {
                                            notificationTypes.value = type
                                            showTypesDialog.value = false
                                        }
                                        .padding(8.dp)
                                )
                            }
                        }
                    },
                    confirmButton = {}
                )
            }
        }
    }
}

@Composable
fun ListItem(text: String, trailing: @Composable (() -> Unit)? = null, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.weight(1f))
        if (trailing != null) {
            trailing()
        }
    }
}
