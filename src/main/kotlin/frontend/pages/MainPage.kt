package org.example.frontend.pages

import com.codeborne.selenide.CollectionCondition.sizeGreaterThan
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.SelenideElement
import frontend.components.list.ProductItem
import frontend.components.list.ProductsItems
import io.qameta.allure.Step
import org.example.frontend.components.HeaderComponent
import org.example.frontend.helpers.Wrappers.Companion.byDataTestGroup
import org.example.frontend.helpers.Wrappers.Companion.byDataTestId

class MainPage {

    private val txtTitle: SelenideElement get() = element(byDataTestId("main-image-text"))
    private val listPopularProducts: ElementsCollection get() = elements(byDataTestGroup("product-card"))


    @Step("Открыть главную страницу")
    fun open(): MainPage = apply { Selenide.open("/") }

    @Step("Получить название кофейни")
    fun getTitle(): String {
        return txtTitle.text
    }

    @Step("Перейти к компоненту Header")
    fun header(): HeaderComponent {
        return HeaderComponent()
    }

    @Step("Перейти к компоненту Header")
    fun navigateHeader(): HeaderComponent {
        return HeaderComponent()
    }

    @Step("Получить список популярных товаров")
    fun getPopularProducts(): List<ProductItem> {
        listPopularProducts.shouldHave(sizeGreaterThan(0))
        return ProductsItems(listPopularProducts).getItems()
    }

}