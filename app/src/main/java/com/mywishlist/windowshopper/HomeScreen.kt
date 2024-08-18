package com.mywishlist.windowshopper

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
    val products by remember { mutableStateOf(DataSource().loadProducts().toMutableList()) }
    var currentIndex by remember { mutableIntStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Home") })
        }
    ) { innerPadding ->
        LazyRow(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(1) { // Display one item at a time
                if (products.isNotEmpty()) {
                    val product = products[currentIndex]
                    // Function to move to the next product
                    fun moveToNextProduct() {
                        if (currentIndex < products.size - 1) {
                            currentIndex++
                        } else {
                            currentIndex = 0 // Optionally loop back to the first product
                        }
                    }

                    SwipeCard(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize(),
                        content = {
                            Column (modifier = Modifier.fillMaxSize()) {
                                // Display the product image
                                Image(
                                    painter = painterResource(id = product.image),
                                    contentDescription = product.name,
                                    contentScale = ContentScale.Crop,
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

                                Spacer(modifier = Modifier.height(8.dp))

                                // Display the product name
                                // Text content at the bottom
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
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
                        },
                        onSwipeRight = {
                            coroutineScope.launch {
                                if (!wishList.contains(product)) {
                                    wishList.add(product)
                                }
                                moveToNextProduct()
                            }
                        },
                        onSwipeLeft = {
                            coroutineScope.launch {
                                moveToNextProduct()
                            }
                        },
                    )
                }
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

    // Animation for offset
    val animatedOffset by animateIntOffsetAsState(
        targetValue = IntOffset(swipeState.offset.value.roundToInt(), 0),
        animationSpec = tween(
            durationMillis = 300, // Adjust this for smoother/faster animations
            easing = LinearOutSlowInEasing
        ), label = ""
    )

    Box(
        modifier = modifier
            .swipeable(
                state = swipeState,
                anchors = mapOf(0f to 0, swipeThresholdPx to 1, -swipeThresholdPx to -1),
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
            .offset { animatedOffset }
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
