package frontend

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.example.frontend.helpers.BaseUiTest
import org.example.frontend.pages.MainPage
import org.example.frontend.pages.ProductData
import org.example.frontend.pages.ProductsPage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class ParamCoffeeTest : BaseUiTest() {
    @ParameterizedTest
    @ValueSource(strings = ["Products", "Orders", "Contact", "Cart"])
    @DisplayName("Проверка наличия ссылок в шапке: {link}")
    fun testWithValueSource(link: String) {
        val listLinks = MainPage().header().getLinks()
        listLinks shouldContain link
    }

    @ParameterizedTest
    @DisplayName("Проверить названия продуктов")
    @ValueSource(strings = ["Coca Cola", "Coffee", "Tea", "Bubble Tea", "Water", "Juice"])
    fun checkProducts(link: String){
        MainPage().header().clickLink("Products")
        val productsLinks = ProductsPage().getProducts()
        productsLinks shouldContain link
    }

    @ParameterizedTest
    @CsvSource(
        """Coca Cola, A wonderful coca cola for your daily brew., $2.33""",
        """Coffee, A wonderful coffee for your daily brew., $4.49""",
        """Tea, A wonderful tea for your daily brew., $3.25""",
        """Bubble Tea,A wonderful bubble tea for your daily brew.,$2""",
        """Water, A wonderful water for your daily brew., $1""",
        """Juice, A wonderful juice for your daily brew., $3.75"""
    )
    @DisplayName("Проверить текст всех продуктов")
    fun checkAllProductsText(product: String, description: String, price: String) {
        val products = ProductsPage().getProductsInfo()
        products shouldContain ProductData(product, description, price)
    }
}