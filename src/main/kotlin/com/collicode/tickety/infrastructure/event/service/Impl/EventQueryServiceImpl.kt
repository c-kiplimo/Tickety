package com.collicode.tickety.infrastructure.event.service.Impl

import com.collicode.tickety.infrastructure.event.dto.EventRequest
import com.collicode.tickety.infrastructure.event.repository.EventReadRepository
import com.collicode.tickety.infrastructure.event.repository.model.EventReadModel
import com.collicode.tickety.infrastructure.event.service.EventQueryService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class EventQueryServiceImpl(
    private val eventReadRepository: EventReadRepository
) : EventQueryService {
    override fun fetchAllEvents(queryMap: Map<String, String>): Flux<EventRequest> {
        return eventReadRepository.fetchAllEvents(queryMap)
            .map { it.toResponse() }
    }
}

private fun EventReadModel.toResponse(): EventRequest {
    return EventRequest(
        eventId = this.recordId,
        organizationId = this.organizationId,
        title = this.title,
        description = this.description,
        date = this.date,
        location = this.location,
        ticketTypes = this.ticketTypes,
        totalCapacity = this.totalCapacity,
    )
}
