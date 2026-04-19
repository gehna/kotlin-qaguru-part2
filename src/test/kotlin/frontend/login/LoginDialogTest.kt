package frontend.login

import frontend.components.popup.JoinDialogPopup
import frontend.components.popup.LoginPopup
import io.kotest.matchers.shouldBe
import org.example.frontend.helpers.BaseUiTest
import org.example.frontend.pages.MainPage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LoginDialogTest: BaseUiTest() {

    @Test
    @DisplayName("Check after clicking 'Your account' link  in 'Join' popup window")
    fun loginDialogPopup() {
        MainPage()
            .header()
            .clickLink("Join")

        JoinDialogPopup()
            .signInPopupClick()

        LoginPopup()
            .getLoginWindowTitle() shouldBe "Login"
    }
}