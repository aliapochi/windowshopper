package com.mywishlist.windowshopper

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.mywishlist.windowshopper.data.DataSource
import com.mywishlist.windowshopper.model.Product
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, wishList: MutableList<Product>) {
    //var wishList = mutableListOf<Product>()
    var products by remember { mutableStateOf(DataSource().loadProducts().toMutableList()) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Home") })
        }
    ) { innerPadding ->
        LazyRow(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(products.size) { index ->
                val product = products[index]

                SwipeCard(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize(),
                    content = {
                        Column(

                        ) {
                            // Display the product image
                            Image(
                                painter = painterResource(id = product.image),
                                contentDescription = product.name,
                                modifier = Modifier
                                    .size(128.dp)
                                    .align(Alignment.CenterHorizontally)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            // Display the product name
                            Text(
                                text = product.name,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            // Display the product description
                            Text(
                                text = product.description,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    },
                    onSwipeRight = {
                        // Add product to wishlist on right swipe
                        coroutineScope.launch {
                            wishList.add(product)
                            products = products.toMutableList().apply { remove(product) }
                        }
                    },
                    onSwipeLeft = {
                        // Handle left swipe (e.g., dislike)
                        coroutineScope.launch {
                            products = products.toMutableList().apply { remove(product) }
                        }
                    },
                )
            }
        }
    }
}


@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun SwipeCard(
    modifier: Modifier = Modifier,
    onSwipeLeft: () -> Unit = {},
    onSwipeRight: () -> Unit = {},
    swipeThreshold: Dp = 200.dp,
    content: @Composable () -> Unit
) {
    val swipeState = rememberSwipeableState(0)
    val scope = rememberCoroutineScope()
    val swipeThresholdPx = with(LocalDensity.current){ swipeThreshold.toPx()}

    Box(
        modifier = modifier
            .swipeable(
                state = swipeState,
                anchors = mapOf(0f to 0, swipeThresholdPx to 1, -swipeThresholdPx to -1),
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
            .offset { IntOffset(swipeState.offset.value.roundToInt(), 0) }
    ) {
        content()

        LaunchedEffect(swipeState.currentValue) {
            if (swipeState.currentValue == 1){
                onSwipeRight()
                scope.launch { swipeState.snapTo(0) }
            }else if (swipeState.currentValue == -1){
                onSwipeLeft()
                scope.launch { swipeState.snapTo(0) }
            }

        }
    }
}
