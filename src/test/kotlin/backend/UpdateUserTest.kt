package backend

import backend.controllers.Controllers
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.example.backend.api.extention.Extensions.Companion.checkIfSuccessful
import org.example.backend.api.extention.Extensions.Companion.getAsObject
import org.example.backend.api.models.users.createUser.defaultUser
import org.example.backend.api.models.users.updateUser.UpdateRequest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class UpdateUserTest: Controllers() {

    @Test
    @DisplayName("Positive check: update all user data")
    fun updateUserWithValidData() {
        val user = users.createUser(defaultUser()).getAsObject()
        val updateUser = UpdateRequest(
            username = "updatedUsername",
            password = "updatedPassword",
            email = "updatedEmail@autotest.com",
            phoneNumber = "88005553535"
        )
        val updatedUser = users.updateUserById(id = user.id, body = updateUser).getAsObject()
        val login = auth.login(email = updateUser.email!!, password = updateUser.password!!).getAsObject()

        login.accessToken.length shouldBeGreaterThan 10
        updatedUser.phoneNumber shouldBe updateUser.phoneNumber
        updatedUser.username shouldBe updateUser.username
        updatedUser.email shouldBe updateUser.email
    }

    @Test
    @DisplayName("Positive check: update user password")
    fun updateUserPassword() {
        val user = users.createUser(defaultUser()).getAsObject()

        val updateUserData = UpdateRequest(password = "updatedPassword")
        users.updateUserById(id = user.id, body = updateUserData).checkIfSuccessful()

        val login = auth.login(email = user.email, password = updateUserData.password!!).getAsObject()

        login.accessToken.length shouldBeGreaterThan 10
    }

    @Test
    @DisplayName("Positive check: update user phone number")
    fun updateUserEmail() {
        val user = users.createUser(defaultUser()).getAsObject()

        val updateUserData = UpdateRequest(phoneNumber = "8(800)5553535")
        users.updateUserById(id = user.id, body = updateUserData).checkIfSuccessful()

        val updatedUser = users.getUserById(id = user.id).getAsObject()

        updatedUser.phoneNumber shouldBe updateUserData.phoneNumber
    }
}