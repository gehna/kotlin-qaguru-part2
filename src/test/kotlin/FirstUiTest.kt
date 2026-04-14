import com.codeborne.selenide.Selenide
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.qameta.allure.TmsLink
import org.example.frontend.helpers.BaseUiTest
import org.example.frontend.pages.MainPage
import org.example.frontend.pages.ProductsPage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class FirstUiTest : BaseUiTest() {

    @Test
    @TmsLink("TEST-1")
    @DisplayName("Проверка названия кофейни на главной странице")
    fun testFirstUI() {
        val title = MainPage()
            .getTitle()

        title shouldBe "Welcome to Brew & Bean"
    }

    @Test
    @DisplayName("Проверка навигации по ссылкам в шапке")
    fun testNavigation() {
        MainPage()
            .header()
            .clickLink("Products")

        val products = ProductsPage()
            .getProducts()

        products.shouldHaveSize(6)
    }
}