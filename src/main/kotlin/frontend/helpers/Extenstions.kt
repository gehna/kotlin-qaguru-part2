package org.example.frontend.helpers

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.SelenideElement

class Extenstions {

    companion object {
        fun SelenideElement.shouldBeVisible(): Boolean {
            this.shouldBe(visible)
            return this.isDisplayed
        }
    }
}