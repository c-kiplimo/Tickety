package com.collicode.tickety.infrastructure.event.service

import com.collicode.tickety.infrastructure.event.dto.EventRequest
import com.collicode.tickety.infrastructure.event.repository.model.EventReadModel
import reactor.core.publisher.Flux

interface EventQueryService {
    fun fetchAllPublicEvents(queryMap: Map<String, String>): Flux<EventRequest>
}