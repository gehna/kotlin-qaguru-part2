package database

import backend.controllers.Controllers
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.example.backend.api.extention.Extensions.Companion.getAsObject
import org.example.backend.helpers.GarbageCollector
import org.example.database.ExposedHelper
import org.example.database.JDBCHelper
import org.example.frontend.components.popup.CreateAccountPopup
import org.example.frontend.helpers.BaseUiTest
import org.example.frontend.pages.MainPage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DbCreateUser: BaseUiTest() {

    private val controllers = Controllers()

    @DisplayName("Create and check user with basic JDBC kotlin helper")
    @ParameterizedTest(name = "Username: {0}, Email: {1}, Password: {2}")
    @CsvSource("'testBasic','testBasic','testBasic'")
    fun testCreateUserWithJdbcHelper(username: String, email: String, password: String) {
        MainPage()
            .navigateHeader()
            .clickLink("Join")

        CreateAccountPopup()
            .joinAs(username, email, password)

        val jdbcClient = JDBCHelper()

        val users = jdbcClient.getUsers().firstOrNull() { it.email == email}
        println(users)

        users?.let {
            GarbageCollector.user.add(it.id)
        }

        users shouldNotBe null
        users?.username shouldBe username
        users?.email shouldBe email

        val apiUser = controllers.users.getUserById(id = users!!.id).getAsObject()
        println(apiUser)

        users shouldNotBe null
        users.username shouldBe apiUser.username
        users.email shouldBe apiUser.email
    }

    @ParameterizedTest(name = "Username: {0}, Email: {1}, Password: {2}")
    @DisplayName("Create and check user with Exposed DB helper")
    @CsvSource("'testExposed','testExposed','testExposed'")
    fun testCreateUserWithExposedHelper(username: String, email: String, password: String) {
        val exposedHelper = ExposedHelper()

        MainPage()
            .navigateHeader()
            .clickLink("Join")

        CreateAccountPopup()
            .joinAs(username, email, password)

        val users = exposedHelper.getAllUsersExposed().firstOrNull() { it.email == email}
        println(users)

        users?.let {
            GarbageCollector.user.add(it.id)
        }

        users shouldNotBe null
        users?.username shouldBe username
        users?.email shouldBe email

        val apiUser = controllers.users.getUserById(id = users!!.id).getAsObject()
        println(apiUser)

        users shouldNotBe null
        users.username shouldBe apiUser.username
        users.email shouldBe apiUser.email
    }
}