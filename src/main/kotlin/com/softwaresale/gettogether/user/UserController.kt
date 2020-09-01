package com.softwaresale.gettogether.user

import com.softwaresale.gettogether.group.Group
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
        // return this.userService.getCurrentUser() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Error while fetching user")
        return this.userService.getCurrentUser() ?: this.userService.createUserFromCurrent() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Error while fetching user")
    }

    @PostMapping("/signup")
    fun signUp(): User {
        return this.userService.createUserFromCurrent() ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while creating user")
    }

    @GetMapping("/me/ownedGroups")
    fun getUserOwnedGroups(): Iterable<Group> {
        return this.userService.getCurrentUser()?.ownedGroups ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist")
    }

    @GetMapping("/me/memberGroups")
    fun getUserMemberGroups(): Iterable<Group> {
        return this.userService.getCurrentUser()?.memberGroups ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist")
    }
}