package com.softwaresale.gettogether.event

import com.fasterxml.jackson.annotation.JsonBackReference
import com.softwaresale.gettogether.group.Group
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
        @OneToMany(cascade = [CascadeType.ALL])
        val rspvs: MutableList<RsvpStatus> = mutableListOf(),
        @Id @GeneratedValue(generator = "system-uuid")
        @GenericGenerator(name = "system-uuid", strategy = "uuid")
        val id: String?,
        @ManyToOne
        @JsonBackReference
        var group: Group?
)