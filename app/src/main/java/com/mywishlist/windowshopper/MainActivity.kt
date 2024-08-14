package com.mywishlist.windowshopper


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mywishlist.windowshopper.model.Product
import com.mywishlist.windowshopper.ui.theme.Purple40
import com.mywishlist.windowshopper.ui.theme.WindowShopperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WindowShopperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavGraph()
                }
            }
        }
    }
}



@Composable
fun AppNavGraph(){
    val navController = rememberNavController()
    val context = LocalContext.current.applicationContext
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Purple40
            ) {
                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Home
                        navController.navigate(Screens.HomeScreen.screen){
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ){
                    Icon(Icons.Default.Home, contentDescription = null, modifier = Modifier.size(26.dp),
                        tint = if(selected.value == Icons.Default.Home) Color.White else Color.DarkGray )
                }

                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Favorite
                        navController.navigate(Screens.WishlistScreen.screen){
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ){
                    Icon(Icons.Default.Favorite, contentDescription = null, modifier = Modifier.size(26.dp),
                        tint = if(selected.value == Icons.Default.Favorite) Color.White else Color.DarkGray )
                }

                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Settings
                        navController.navigate(Screens.SettingsScreen.screen){
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ){
                    Icon(Icons.Default.Settings, contentDescription = null, modifier = Modifier.size(26.dp),
                        tint = if(selected.value == Icons.Default.Settings) Color.White else Color.DarkGray )
                }

            }
        }

    ){paddingValues ->
        NavHost(navController = navController,
            startDestination = Screens.HomeScreen.screen,
            modifier = Modifier.padding(paddingValues)){
            composable(Screens.HomeScreen.screen){ HomeScreen()}
            composable(Screens.WishlistScreen.screen){ WishlistScreen()}
            composable(Screens.SettingsScreen.screen){ SettingsScreen()}

        }

    }
}
@Preview()
@Composable
fun WindowShopperPreview(){
    WindowShopperTheme{
        AppNavGraph()
    }

}
