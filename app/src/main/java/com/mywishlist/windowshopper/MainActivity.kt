package com.mywishlist.windowshopper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mywishlist.windowshopper.data.OfflineProductsRepository
import com.mywishlist.windowshopper.data.ProductDatabase
import com.mywishlist.windowshopper.ui.theme.ProductScreen
import com.mywishlist.windowshopper.ui.theme.WindowShopperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Initialize Database
        val database = ProductDatabase.getDatabase(this)
        val productRepository = OfflineProductsRepository(database.productDao())
        setContent {
            WindowShopperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WindowShopperApp {
                        //ProductScreen(repository = productRepository)
                        TestMyAppDisplay(modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}

@Composable
fun TestMyAppDisplay(modifier: Modifier){
    Column(modifier = Modifier.fillMaxWidth()
        ){
        Text("Just Testing")

    }
}

@Composable
fun WindowShopperApp(content: @Composable () -> Unit){
    MaterialTheme{
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun WindowShopperPreview() {

}