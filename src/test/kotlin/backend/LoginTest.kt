package backend

import backend.api.models.ErrorResponse
import backend.controllers.Controllers
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe

import org.example.backend.api.extention.Extensions.Companion.getAsObject
import org.example.backend.api.extention.Extensions.Companion.getErrorAsObject

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LoginTest : Controllers() {

    @Test
    @DisplayName("Test Login with valid credentials should return access token and refresh token")
    fun testLoginWithValidCredentials() {
        val response = auth.login("r@autotest.com", "r").getAsObject()

        response.accessToken.length shouldBeGreaterThan 10
        response.refreshToken.length shouldBeGreaterThan 10

    }

    @Test
    @DisplayName("Login with invalid credentials should return error")
    fun testLoginWithInvalidCredentials() {
        val response = auth.login("invalid", "credentials").getErrorAsObject<ErrorResponse>()

        response.code shouldBe 400
        response.reason shouldBe "Invalid email or password"
    }


}