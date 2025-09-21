package com.collicode.tickety.infrastructure.event.service

import com.collicode.tickety.infrastructure.event.dto.EventRequest
import reactor.core.publisher.Flux

interface EventQueryService {
    fun fetchAllEvents(queryMap: Map<String, String>): Flux<EventRequest>
}