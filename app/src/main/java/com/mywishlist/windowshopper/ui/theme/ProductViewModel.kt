package com.mywishlist.windowshopper.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mywishlist.windowshopper.data.Product
import com.mywishlist.windowshopper.data.ProductsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first

class ProductViewModel(private val repository: ProductsRepository) : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    init {
        viewModelScope.launch {
            initializeData()
            repository.getAllProductsStream().collect { productList ->
                _products.value = productList
            }
        }
    }

    private suspend fun initializeData() {
        // Check if the data already exists and only insert default data if necessary
        val existingProducts = repository.getAllProductsStream().first()
        if (existingProducts.isEmpty()) {
            repository.insertDefaultData()
        }
    }

    fun likeProduct(product: Product) {
        viewModelScope.launch {
            try {
                repository.updateProduct(product.copy(liked = true))
            } catch (e: Exception) {
                // Handle error (e.g., log the error or show a message to the user)
            }
        }
    }

    fun dislikeProduct(product: Product) {
        viewModelScope.launch {
            try {
                repository.updateProduct(product.copy(liked = false))
            } catch (e: Exception) {
                // Handle error (e.g., log the error or show a message to the user)
            }
        }
    }
}
