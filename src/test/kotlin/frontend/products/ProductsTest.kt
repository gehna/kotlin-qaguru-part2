package frontend

import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.example.backend.helpers.ProductHelper
import org.example.frontend.helpers.BaseUiTest
import org.example.frontend.helpers.Extenstions.Companion.toMoney
import org.example.frontend.pages.MainPage
import org.example.frontend.pages.ProductsPage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ProductsTest : BaseUiTest() {

    val productsHelper = ProductHelper()

    @Test
    @DisplayName("Create and check products")
    fun testFiveProductsExist() {

        val listOfproducts = productsHelper.createCoffeeProduct(4)

        val products = ProductsPage()
            .open()
            .getProductsInfo()

        products.size shouldBe 4
        products.forEachIndexed { index, product ->
            product.name shouldBe listOfproducts[index].name
        }
    }

    @Test
    @DisplayName("Проверка заголовка страницы Products")
    fun testProductsPageTitle() {
        MainPage()
            .header()
            .clickLink("Products")
        val title = ProductsPage()
            .getTitle()

        title shouldBe "All Products"
    }

    @Test
    @DisplayName("Проверка что популярные продукты, есть на странице Products")
    fun testPopularProducts() {
        MainPage()
            .getPopularProducts().first()
            .btnIncrement
            .click()

        MainPage()
            .header()
            .clickLink("Products")

        val firstProductsItem = ProductsPage()
            .getProductsInfo().first()

        val firstPopularProduct = MainPage().getPopularProducts().first()

        firstPopularProduct.name shouldBe firstProductsItem.name
        firstPopularProduct.description shouldBe firstProductsItem.description
        firstPopularProduct.price shouldBe firstProductsItem.price.toMoney()
        firstPopularProduct.quantity shouldBe firstProductsItem.quantity.toInt()
    }

    @Test
    @DisplayName("Сравнение популярных товаров с товарами на странице Products")
    fun testAllProducts() {
        val popularProducts = MainPage()
            .getPopularProducts()
            .map { Triple(it.name, it.price, it.description) }

        MainPage()
            .header()
            .clickLink("Products")

        val allProductsItems = ProductsPage()
            .getProductsInfo()
            .map { Triple(it.name, it.price.toMoney(), it.description) }

        allProductsItems shouldContainAll popularProducts

    }
}