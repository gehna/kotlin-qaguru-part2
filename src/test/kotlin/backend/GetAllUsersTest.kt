package backend

import backend.controllers.Controllers
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.equals.shouldNotBeEqual
import io.kotest.matchers.shouldBe
import org.example.backend.api.extention.Extensions.Companion.getAsObject
import org.example.backend.api.models.users.createUser.defaultUser
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GetAllUsersTest: Controllers() {

    @Test
    @DisplayName("Get requested user from all users")
    fun getUserFromAllUsers() {
        val user = users.createUser(defaultUser()).getAsObject()
        val allUsers = users.getAllUsers().getAsObject()

        allUsers shouldContain user
    }

    @Test
    @DisplayName("Get a limited number of users")
    fun checkLimitUsers() {
        val allUsers = users.getAllUsers().getAsObject()
        allUsers.size shouldBeEqual 48

        val oneUser = users.getAllUsers(offset = 0, limit = 1).getAsObject()
        oneUser.size shouldBeEqual 1

        val overCap = users.getAllUsers(offset = 0, limit = 51).getAsObject()
        overCap.size shouldBe 48

        val firstTenUsers = users.getAllUsers(offset = 0, limit = 10).getAsObject()
        val secondTenUsers = users.getAllUsers(offset = 11, limit = 10).getAsObject()
        val firstTenIds = firstTenUsers.map { it.id }.toSet()
        val secondTenIds = secondTenUsers.map { it.id }.toSet()

        firstTenIds shouldNotBeEqual secondTenIds
    }

    @Test
    @DisplayName("Get a limited number of unique users per page")
    fun getPaginatedUsers() {
        val page1 = users.getAllUsers(offset = 0, limit = 10).getAsObject()
        val page2 = users.getAllUsers(offset = 10, limit = 10).getAsObject()
        val page3 = users.getAllUsers(offset = 20, limit = 10).getAsObject()
        val page4 = users.getAllUsers(offset = 30, limit = 10).getAsObject()
        val page5 = users.getAllUsers(offset = 40, limit = 10).getAsObject()

        page1.size shouldBe 10
        page2.size shouldBe 10
        page3.size shouldBe 10
        page4.size shouldBe 10
        page5.size shouldBe 8

        val page1Ids = page1.map { it.id }.toSet()
        val page2Ids = page2.map { it.id }.toSet()
        val page3Ids = page3.map { it.id }.toSet()
        val page4Ids = page4.map { it.id }.toSet()
        val page5Ids = page5.map { it.id }.toSet()

        val allIds = page1Ids + page2Ids + page3Ids + page4Ids + page5Ids
        allIds.size shouldBe 48
    }
}
