package com.softwaresale.gettogether.event

import org.hibernate.annotations.GenericGenerator
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
class RsvpStatus(
        val userId: String,
        val displayName: String,
        val attending: Boolean,
        @Id @GeneratedValue
        val id: Long?
)

@Entity
class Event(
        val name: String,
        val time: ZonedDateTime,
        @OneToMany
        val rspvs: MutableList<RsvpStatus> = mutableListOf(),
        @Id @GeneratedValue(generator = "system-uuid")
        @GenericGenerator(name = "system-uuid", strategy = "uuid")
        val id: String?
)