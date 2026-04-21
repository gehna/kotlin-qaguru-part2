package frontend.cart

import frontend.components.list.ProductItem
import io.kotest.matchers.equality.shouldBeEqualToDifferentTypeIgnoringFields
import org.example.frontend.helpers.BaseUiTest
import org.example.frontend.pages.MainPage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.collections.first


class CartTest : BaseUiTest() {

    @Test
    @DisplayName("Check products in cart")
    fun testProductsInCart() {
        val firstPopularItem = MainPage()
            .open()
            .getPopularProducts()
            .first()
        firstPopularItem.btnIncrement.click()

        val firstCartItem = MainPage()
            .navigateHeader()
            .clickLink("Cart")
            .navigateCartPopup()
            .getCartProducts()
            .first()

        firstPopularItem.apply { quantity = 1 }.shouldBeEqualToDifferentTypeIgnoringFields(
            other = firstCartItem,
            property = ProductItem::description,
            ProductItem::btnDecrement,
            ProductItem::btnIncrement,
            ProductItem::image
        )
    }
}