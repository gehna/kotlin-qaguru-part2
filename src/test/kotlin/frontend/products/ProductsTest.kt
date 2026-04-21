package frontend


import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.example.frontend.helpers.BaseUiTest
import org.example.frontend.pages.MainPage
import org.example.frontend.pages.ProductsPage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ProductsTest : BaseUiTest() {

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
            .getPopularProducts()[1]
            .btnIncrement
            .click()

        MainPage()
            .header()
            .clickLink("Products")

        val secondProductsItem = ProductsPage()
            .getProductsInfo()[1]

        val secondPopularProduct = MainPage().getPopularProducts()[1]

        secondPopularProduct shouldBeEqual secondProductsItem
        secondPopularProduct.quantity shouldBeEqual secondProductsItem.quantity
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
            .map { Triple(it.name, it.price, it.description) }

        allProductsItems shouldContainAll popularProducts

    }
}