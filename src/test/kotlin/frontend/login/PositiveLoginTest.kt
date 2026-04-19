package frontend.login

import frontend.components.popup.JoinDialogPopup
import frontend.components.popup.LoginPopup
import io.kotest.matchers.shouldBe
import org.example.frontend.helpers.BaseUiTest
import org.example.frontend.pages.MainPage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PositiveLoginTest: BaseUiTest() {

    @DisplayName("Parametrized login validation positive test")
    @ParameterizedTest(name = "Email {0}, Password: {1}")
    @CsvSource(
        "'w@autotest.com', 'w'"
    )
    fun loginValidation(email: String, password: String) {

        MainPage()
            .navigateHeader()
            .clickLink("Join")

        JoinDialogPopup()
            .signInPopupClick()

        LoginPopup()
            .loginWindowInput(email, password)

        val isVisible = MainPage().navigateHeader().checkUserPic()
        isVisible shouldBe true
    }
}