import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController


@Composable
fun AppNavGraph(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){
        composable("home"){HomeScreen(navController)}
        composable("wishlist"){WishlistScreen(navController)}
        composable("setting"){Setting(navController)}
    }
}