package com.collicode.tickety.infrastructure.event.repository

import com.collicode.tickety.infrastructure.event.dto.TicketType
import com.collicode.tickety.infrastructure.event.repository.model.EventWriteModel
import reactor.core.publisher.Mono

interface EventWriteRepository {


    fun createEvent(model: EventWriteModel): Mono<Unit>
}