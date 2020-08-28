package com.softwaresale.gettogether.event

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRepository : JpaRepository<Event, String>