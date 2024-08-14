package com.mywishlist.windowshopper

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
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
            // Account Management Section
            Text("Account Management", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp))
            Divider()
            ListItem(text = "Profile Information", onClick = { /* Navigate to Profile Screen */ })
            ListItem(text = "Log Out", onClick = { /* Handle Logout */ })

            // Notification Settings Section
            Spacer(modifier = Modifier.height(16.dp))
            Text("Notification Settings", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp))
            Divider()
            ListItem(text = "Enable Notifications", trailing = { Switch(checked = true, onCheckedChange = {}) }, onClick = {})
            ListItem(text = "Notification Frequency", onClick = { /* Show Frequency Options */ })
            ListItem(text = "Notification Types", onClick = { /* Show Notification Types Options */ })

            // Wishlist Preferences Section
            Spacer(modifier = Modifier.height(16.dp))
            Text("Wishlist Preferences", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp))
            Divider()
            ListItem(text = "Auto-Remove Liked Items", trailing = { Switch(checked = true, onCheckedChange = {}) },onClick = {})
            ListItem(text = "Sort Wishlist", onClick = { /* Show Sorting Options */ })

            // Appearance Section
            Spacer(modifier = Modifier.height(16.dp))
            Text("Appearance", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp))
            Divider()
            ListItem(text = "Theme", onClick = { /* Show Theme Selection */ })
            ListItem(text = "Font Size", onClick = { /* Show Font Size Options */ })

            // Data Management Section
            Spacer(modifier = Modifier.height(16.dp))
            Text("Data Management", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp))
            Divider()
            ListItem(text = "Clear Wishlist", onClick = { /* Clear Wishlist */ })
            ListItem(text = "Export Data", onClick = { /* Export Wishlist */ })
            ListItem(text = "Import Data", onClick = { /* Import Wishlist */ })

            // Support Section
            Spacer(modifier = Modifier.height(16.dp))
            Text("Support", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp))
            Divider()
            ListItem(text = "Contact Us", onClick = { /* Open Email */ })
            ListItem(text = "FAQ", onClick = { /* Show FAQ */ })
            ListItem(text = "App Version: 1.0.0", onClick = { /* Do Nothing */ })
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
