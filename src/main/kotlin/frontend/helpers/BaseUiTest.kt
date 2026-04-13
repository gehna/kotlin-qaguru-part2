package org.example.frontend.helpers

import com.codeborne.selenide.Selenide
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class BaseUiTest {

    @BeforeEach
    fun openBrowser() {
        Selenide.open("https://google.com")
    }

    @AfterEach
    fun clearBrowser() {
        Selenide.clearBrowserCookies()
        Selenide.clearBrowserLocalStorage()
    }
}