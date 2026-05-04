package frontend.products

import backend.controllers.Controllers
import io.kotest.matchers.equals.shouldBeEqual
import org.example.backend.api.extention.Extensions.Companion.getAsObject
import org.example.backend.helpers.AuthorizationHelper
import org.example.backend.helpers.ProductHelper
import org.example.frontend.helpers.BaseUiTest
import org.example.frontend.pages.ProductsPage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CreateProductTest: BaseUiTest() {
    private val controllers = Controllers()
    val productsHelper = ProductHelper()
    val authHelper = AuthorizationHelper()

    @Test
    @DisplayName("Create and check created products")
    fun createAndCheckProducts() {

        productsHelper.createTeaProduct(4)

        val userToken = authHelper.getNewToken()
        productsHelper.addProduct(name = "Coffee", count = 5, token = userToken)


        val backendCount = controllers.products.getProducts().getAsObject()
            .filter { it.name.contains("Coffee", ignoreCase = true) }.size

        val frontendCount = ProductsPage()
            .open()
            .getProductsInfo()
            .filter { it.name.contains("Coffee", ignoreCase = true) }.size

        backendCount shouldBeEqual frontendCount
    }
}