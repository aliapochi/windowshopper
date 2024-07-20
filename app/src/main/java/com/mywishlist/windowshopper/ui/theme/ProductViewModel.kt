package com.mywishlist.windowshopper.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mywishlist.windowshopper.data.Product
import com.mywishlist.windowshopper.data.ProductsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductsRepository): ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products
    
    init {
        viewModelScope.launch {
            repository.getAllProductsStream().collect{
                _products.value = it
            }
        }
    }
     fun likeProduct(product: Product){
         viewModelScope.launch {
             repository.updateProduct(product.copy(liked = true))
         }
     } fun dislikeProduct(product: Product){
         viewModelScope.launch {
             repository.updateProduct(product.copy(liked = false))
         }
     }
}