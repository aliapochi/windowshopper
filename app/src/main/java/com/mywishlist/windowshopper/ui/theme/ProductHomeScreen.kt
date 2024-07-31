package com.mywishlist.windowshopper.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.mywishlist.windowshopper.R
import com.mywishlist.windowshopper.data.Product
import com.mywishlist.windowshopper.data.ProductsRepository

@Composable
fun ProductScreen(repository: ProductsRepository){
    val viewModel: ProductViewModel = viewModel(
        factory = ProductViewModelFactory(repository)
    )

    val products by viewModel.products.collectAsState()

    //UI to display and swipe products
    LazyColumn {
        items(products) { product ->
            ProductItem(
                product = product,
                onLike = { viewModel.likeProduct(product)},
                onDislike = { viewModel.dislikeProduct(product)}

            )

        }
    }

}

@Composable
fun ProductItem(product: Product, onLike:() -> Unit, onDislike: () -> Unit) {
    val context = LocalContext.current
    val imageId = context.resources.getIdentifier(product.image, "drawable", context.packageName)

    val actualImageId = if (imageId != 0) {
        imageId
    } else {
        R.drawable.ic_grain // Use a default image if the resource ID is not found
    }

    Box(
        modifier = Modifier.
            fillMaxWidth().
            padding(16.dp).
            background(MaterialTheme.colorScheme.surface).
            clickable {  }
    ){
        Column {
            Text(product.name)
            Text(product.description)
            Image(painter = painterResource(actualImageId), contentDescription = null,
                modifier = Modifier.size(128.dp))
            Row {
                Button(onClick = onLike){
                    Text("Like")

                }
                Button(onClick = onDislike){
                    Text("Dislike")
                }
            }
        }

    }

}
