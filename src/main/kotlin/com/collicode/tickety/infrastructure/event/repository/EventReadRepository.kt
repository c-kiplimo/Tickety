package com.collicode.tickety.infrastructure.event.repository

import com.collicode.tickety.infrastructure.event.repository.model.EventReadModel
import reactor.core.publisher.Flux

interface EventReadRepository {
    fun fetchAllEvents(queryMap: Map<String, String>): Flux<EventReadModel>
}