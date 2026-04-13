import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import org.example.frontend.helpers.BaseUiTest
import org.junit.jupiter.api.Test

class FirstUiTest : BaseUiTest() {

    @Test
    fun testFirstUI() {
        val searchInput = element("[name='q']")
        searchInput.value = "Selenide"
        searchInput.pressEnter()
        Selenide.sleep(5000)
    }
}