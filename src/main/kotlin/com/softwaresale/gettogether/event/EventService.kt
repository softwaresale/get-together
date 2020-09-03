package com.softwaresale.gettogether.event

import com.softwaresale.gettogether.user.User
import org.springframework.stereotype.Service

@Service
class EventService(
        private val eventRepository: EventRepository
) {
    fun getAll(): Iterable<Event> = eventRepository.findAll()

    fun getById(id: String): Event? {
        return with(eventRepository.findById(id)) {
            if (this.isPresent) this.get() else null
        }
    }

    fun save(event: Event) = eventRepository.save(event)

    fun addRsvp(event: Event, rsvpStatus: RsvpStatus): Event {
        event.rsvps.add(rsvpStatus)
        return eventRepository.saveAndFlush(event)
    }

    fun addRsvpForStatus(event: Event, user: User, attending: Boolean): Event {
        val rsvpStatus = with(user) {
            RsvpStatus(this.id, this.name, attending, null)
        }

        return addRsvp(event, rsvpStatus)
    }
}