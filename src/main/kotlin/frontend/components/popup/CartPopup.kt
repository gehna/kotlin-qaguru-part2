package frontend.components.popup

import CartItem
import CartItems
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import io.qameta.allure.Step
import org.example.frontend.helpers.Wrappers.Companion.byDataTestGroup
import org.example.frontend.helpers.Wrappers.Companion.byDataTestId

class CartPopup {
    private val cartPopupTotalSum get() = element(byDataTestId("cart-total-price"))
    private val cartCheckoutButton get() = element(byDataTestId("cart-checkout"))
    private val newCartItems get() = elements(byDataTestGroup("cart-item"))

    @Step("Получить список товаров в корзине")
    fun getCartProducts(): List<CartItem> {
        return CartItems(newCartItems).getItems()
    }

    @Step("Check sum value in popup window")
    fun cartShouldHaveTotalSum(): String {
        return cartPopupTotalSum.text
    }

    @Step("Checkout button text check")
    fun cartButtonText(): String {
        return cartCheckoutButton.text
    }

    @Step("Get cart total price")
    fun getTotalPrice(): Float {
        return cartPopupTotalSum.text.filter {it.isDigit()}.toFloat() / 100f
    }
}