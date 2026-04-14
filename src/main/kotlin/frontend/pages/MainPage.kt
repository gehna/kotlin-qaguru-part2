package org.example.frontend.pages

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.SelenideElement
import io.qameta.allure.Step
import org.example.frontend.components.HeaderComponent
import org.example.frontend.helpers.Wrappers.Companion.byDataTestId

class MainPage {

    private val txtTitle: SelenideElement get() = element(byDataTestId("main-image-text"))

    @Step("Открыть главную страницу")
    fun open(){
        Selenide.open("/")
    }

    @Step("Получить название кофейни")
    fun getTitle(): String {
        return txtTitle.text
    }

    @Step("Перейти к компоненту Header")
    fun header(): HeaderComponent {
        return HeaderComponent()
    }

}