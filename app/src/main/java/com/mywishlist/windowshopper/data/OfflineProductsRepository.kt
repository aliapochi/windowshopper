package com.mywishlist.windowshopper.data

import kotlinx.coroutines.flow.Flow

class OfflineProductsRepository(private val productDao: ProductDao): ProductsRepository {
    override fun getAllProductsStream(): Flow<List<Product>> = productDao.getAllProducts()


    override fun getProductStream(id: Int): Flow<Product?> = productDao.getProduct(id)

    override suspend fun insertProduct(product: Product) = productDao.insertProduct(product)

    override suspend fun updateProduct(product: Product) = productDao.updateProduct(product)

}