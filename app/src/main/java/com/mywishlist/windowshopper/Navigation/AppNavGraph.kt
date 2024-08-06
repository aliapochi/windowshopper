import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mywishlist.windowshopper.Screens.HomeScreen
import com.mywishlist.windowshopper.Screens.SettingsScreen
import com.mywishlist.windowshopper.Screens.WishlistScreen


@Composable
fun AppNavGraph(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){
        composable("home"){ HomeScreen(navController) }
        composable("wishlist"){ WishlistScreen(navController) }
        composable("settings"){ SettingsScreen(navController) }
    }
}




