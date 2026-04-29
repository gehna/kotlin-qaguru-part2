package backend.controllers

import org.example.kotlin.backend.controllers.AuthController

open class Controllers {
    protected val auth get() = AuthController()
}