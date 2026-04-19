package frontend.login

import frontend.components.popup.JoinDialogPopup
import frontend.components.popup.LoginPopup
import org.example.frontend.helpers.BaseUiTest
import org.example.frontend.pages.MainPage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class NegativeLoginTest: BaseUiTest() {

    @DisplayName("Parametrized login validation negative test")
    @ParameterizedTest(name = "Email {0}, Password: {1}, Error: {2}")
    @CsvSource(
        "'', '', 'Please enter email and password'",
        "'1@1.com', '', 'Please enter email and password'",
        "'1@1.com', '111', 'Invalid email or password'"
    )
    fun loginValidation(email: String, password: String, expectedError: String) {

        MainPage()
            .navigateHeader()
            .clickLink("Join")

        JoinDialogPopup()
            .signInPopupClick()

        LoginPopup()
            .loginWindowInput(email, password)
            .getErrorText(expectedError)
    }
}