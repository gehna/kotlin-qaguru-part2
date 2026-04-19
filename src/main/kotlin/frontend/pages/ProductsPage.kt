package org.example.frontend.pages

import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.SelenideElement
import io.qameta.allure.Step
import org.example.frontend.helpers.Wrappers.Companion.byDataTestGroup
import org.example.frontend.helpers.Wrappers.Companion.byDataTestId

data class ProductData(
    val name: String,
    val description: String,
    val price: String
)

class ProductsPage {
    private val txtTitle: SelenideElement get() = element(byDataTestId("products-title"))
    private val listItems get() = elements(byDataTestGroup("product-card"))
    val listCards: ElementsCollection get() = elements(byDataTestGroup("product-card"))


    fun productName(card: SelenideElement) = card.find(byDataTestGroup("product-card-name"))
    fun itemsDescription(card: SelenideElement) = card.find(byDataTestGroup("product-card-description"))
    fun itemsPrice(card: SelenideElement) = card.find(byDataTestGroup("product-card-price"))

    @Step("Получить название страницы продуктов")
    fun getTitle(): String {
        return txtTitle.text
    }

    fun getProducts(): List<String> {
        listItems.shouldHave(CollectionCondition.sizeGreaterThan(0))
        return listItems.map { card -> productName(card).text.trim() }
    }

    @Step("Get products list from page")
    fun getProductsItems(): ElementsCollection {
        return listItems
    }


    @Step ("Получить информацию из карточек продуктов")
    fun getProductsInfo(): List<ProductData> {
        MainPage().header().clickLink("Products")
        listCards.shouldHave(CollectionCondition.sizeGreaterThan(0))
        return listCards.map { card ->
            ProductData(
                name = productName(card).text,
                description = itemsDescription(card).text,
                price = itemsPrice(card).text
            )
        }
    }

}