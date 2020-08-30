package com.softwaresale.gettogether.group

import com.softwaresale.gettogether.event.Event
import com.softwaresale.gettogether.user.User
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/groups")
class GroupController(
        private val groupService: GroupService
) {
    @GetMapping
    fun getAll(): Iterable<Group> {
        return this.groupService.getAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Group {
        return this.groupService.getById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Group does not exist")
    }

    @PostMapping
    fun create(@RequestBody group: Group): Group {
        return this.groupService.create(group) ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not access current user")
    }

    @GetMapping("/{id}/members")
    fun getGroupMembers(@PathVariable id: Long): Iterable<User> {
        return this.groupService.getById(id)?.members ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Requested group does not exist")
    }

    @PutMapping("/{id}/members")
    fun addUserToGroup(@PathVariable id: Long, @RequestBody user: User): Group {
        return groupService.getById(id)?.let {
            groupService.addUserToGroup(it, user)
        } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Requested group does not exist")
    }

    @GetMapping("/{id}/events")
    fun getGroupEvents(@PathVariable id: Long): Iterable<Event> {
        return this.groupService.getById(id)?.events ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Requested group does not exist")
    }

    @PutMapping("/{id}/events")
    fun addGroupEvent(@PathVariable id: Long, @RequestBody event: Event): Group {
        return this.groupService.getById(id)?.let {
            this.groupService.addEventToGroup(it, event)
        } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Request group does not exist")
    }
}