package backend

import backend.controllers.Controllers
import io.kotest.matchers.shouldBe
import org.example.backend.api.extention.Extensions.Companion.getAsObject
import org.example.backend.api.extention.Extensions.Companion.toBearer
import org.example.backend.api.models.users.createUser.defaultUser
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DeleteUserTest: Controllers() {
    @Test
    @DisplayName("Delete user with valid token should return 200")
    fun testDeleteUserWithValidToken() {
        val user = users.createUser(defaultUser()).getAsObject()
        val login = auth.login("admin","admin").getAsObject()
        val delete = users.deleteUserById(login.accessToken.toBearer(), user.id)

        println(delete.raw())
        delete.code() shouldBe 200
    }
}