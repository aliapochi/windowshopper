package com.mywishlist.windowshopper.data

import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    //Retrieve all Products from Database
    fun getAllProductsStream(): Flow<List<Product>>

    //Retrieve a Product from Database
     fun getProductStream(id: Int): Flow<Product?>


    //Insert a Product into Database
    suspend fun insertProduct(product:Product)

    //Update a product
    suspend fun updateProduct(product: Product)
}