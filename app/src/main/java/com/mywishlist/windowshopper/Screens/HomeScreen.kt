package com.mywishlist.windowshopper.Screens

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mywishlist.windowshopper.data.DataSource
import com.mywishlist.windowshopper.model.Product
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    val products = DataSource().loadProducts()
    val coroutineScope = rememberCoroutineScope()
    val wishList = remember{ mutableStateListOf<Product>() }
    
    Scaffold(
        topBar = {
            TopAppBar(title ={Text("Home")} )
            
        }
    ){innerPadding ->
        LazyRow(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(products.size){ index ->
                val product = products[index]
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize()
                        .pointerInput(Unit){
                            detectDragGestures { change, dragAmount ->
                                val (x, _) = dragAmount
                                if(x > 50){
                                    coroutineScope.launch{
                                        wishList.add(product)
                                    }
                                }else if(x< -50){
                                    coroutineScope.launch {

                                    }

                                }
                                change.consume()
                            }
                        }

                ){
                    Column(){


                    }

                }

            }
        }

    }

}