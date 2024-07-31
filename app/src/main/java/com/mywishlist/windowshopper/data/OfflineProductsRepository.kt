package com.mywishlist.windowshopper.data

import com.mywishlist.windowshopper.R
import kotlinx.coroutines.flow.Flow

class OfflineProductsRepository(private val productDao: ProductDao): ProductsRepository {
    override fun getAllProductsStream(): Flow<List<Product>> = productDao.getAllProducts()


    override fun getProductStream(id: Int): Flow<Product?> = productDao.getProduct(id)

    override suspend fun insertProduct(product: Product) = productDao.insertProduct(product)

    override suspend fun updateProduct(product: Product) = productDao.updateProduct(product)

    override suspend fun insertDefaultData() {
        val defaultProducts = listOf(
            Product(1, name = "Sample Product 1", "ic_launcher_background", description = "Sample description 1", liked = false, isDefault = true),
            Product(2, name = "Sample Product 2", "ic_launcher_background", description = "Sample description 2", liked = false, isDefault = true)
            // Add more default products here
        )
        productDao.insertAllProducts(defaultProducts)
    }

}