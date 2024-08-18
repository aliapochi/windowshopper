package com.mywishlist.windowshopper

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mywishlist.windowshopper.model.Product


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen(navController: NavController, wishList: List<Product>, onShareWishlist: () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Wishlist") },
                actions = {
                    IconButton(onClick = onShareWishlist) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "Share Wishlist")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            items(wishList.size) { index ->
                val product = wishList[index]
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Image covering the entire background
                        Image(
                            painter = painterResource(id = product.image),
                            contentDescription = product.name,
                            contentScale = ContentScale.Crop, // Ensures image fills the card
                            modifier = Modifier.fillMaxSize()
                        )

                        // Overlay with a gradient for better readability
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black.copy(alpha = 0.7f)
                                        )
                                    )
                                )
                        )

                        // Text content at the bottom
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(16.dp)
                        ) {
                            Text(
                                text = product.name,
                                style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = product.description,
                                style = MaterialTheme.typography.bodySmall.copy(color = Color.White),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun WishlistScreenWithShare(navController: NavController, wishList: List<Product>) {
    val context = LocalContext.current

    WishlistScreen(navController = navController, wishList = wishList, onShareWishlist = {
        shareWishlist(context, wishList)
    })
}

fun shareWishlist(context: Context, wishList: List<Product>) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, generateWishlistText(wishList))
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share Wishlist via"))
}

fun generateWishlistText(wishList: List<Product>): String {
    return wishList.joinToString(separator = "\n\n") { product ->
        "${product.name}\n${product.description}"
    }
}



