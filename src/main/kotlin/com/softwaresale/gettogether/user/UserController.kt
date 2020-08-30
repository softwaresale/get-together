package com.softwaresale.gettogether.user

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/user")
class UserController(
        private val userService: UserService
) {

    @GetMapping("/me")
    fun getCurrentUser(): User {
        return this.userService.getCurrentUser() ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while fetching user")
    }

    @PostMapping("/signup")
    fun signUp(): User {
        return this.userService.createUserFromCurrent() ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while creating user")
    }
}