package backend


import backend.api.models.ErrorResponse

import backend.controllers.Controllers
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.example.backend.api.extention.Extensions.Companion.getAsObject
import org.example.backend.api.extention.Extensions.Companion.getErrorAsObject
import org.example.backend.api.models.users.createUser.CreateUserErrors.duplicateCredentials
import org.example.backend.api.models.users.createUser.CreateUserErrors.emptyCredentials
import org.example.backend.api.models.users.createUser.CreateUserErrors.invalidCredentials
import org.example.backend.api.models.users.createUser.CreateUserRequest
import org.example.backend.api.models.users.createUser.defaultUser
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CreateUserTest: Controllers() {

    @Test
    @DisplayName("Positive check: create user with valid credentials")
    fun testUsersCreate() {
        val user = users.createUser(defaultUser()).getAsObject()
        val expectedUser = users.getUserById(id = user.id)

        expectedUser shouldBeEqualToComparingFields user
    }

    @Test
    @DisplayName("Negative check: creating user with invalid credentials should return error")
    fun testUsersCreateWithInvalidCredentials() {
        val response = users.createUser(
            CreateUserRequest(
                username = "1!",
                email = "1@",
                password = "1-"
            )
        )

        val error = response.getErrorAsObject<ErrorResponse>()

        error shouldNotBe null
        error shouldBe invalidCredentials
    }

    @Test
    @DisplayName("Negative check: creating user with existing credentials should return error")
    fun testUsersCreateAlreadyExistingCredentials() {
        val response = users.createUser(
            CreateUserRequest(
                username = "admin",
                email = "admin",
                password = "admin"
            )
        )

        val error = response.getErrorAsObject<ErrorResponse>()

        error shouldNotBe null
        error shouldBe duplicateCredentials
    }


    @ParameterizedTest(name = "Username: {0}, Email {1}, Password: {2}")
    @DisplayName("Negative check: creating user with empty credentials should return error")
    @CsvSource(
        "'', '', ''",
        "'user', '', ''",
        "'user', '1@1.com', ''")
    fun testUsersCreateEmptyCredentials(username: String, email: String, password: String) {
        val response = users.createUser(
            CreateUserRequest(
                username = username,
                email = email,
                password = password
            )
        )

        val error = response.getErrorAsObject<ErrorResponse>()

        error shouldNotBe null
        error shouldBe emptyCredentials
    }
}