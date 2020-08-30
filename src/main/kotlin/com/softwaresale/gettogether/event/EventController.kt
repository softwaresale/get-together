package com.softwaresale.gettogether.event

import com.softwaresale.gettogether.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/events")
class EventController(
        private val eventService: EventService,
        private val userService: UserService
) {
    @GetMapping
    fun getAll() = eventService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): Event {
        return this.eventService.getById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Event does not exist")
    }

    @PostMapping
    fun create(@RequestBody event: Event) = eventService.save(event)

    @PutMapping("/{id}/rsvp")
    fun addRsvp(@PathVariable id: String, @RequestParam attending: Boolean): Event {
        val user = this.userService.getCurrentUser() ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Current user does not exist")
        return this.eventService.getById(id)?.let {
            eventService.addRsvpForStatus(it, user, attending)
        } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Event does not exist")
    }
}