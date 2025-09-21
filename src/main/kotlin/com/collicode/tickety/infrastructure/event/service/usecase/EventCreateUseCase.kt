package com.collicode.tickety.infrastructure.event.service.usecase

import com.collicode.common.service.IdService
import com.collicode.common.service.actions.ActionWriteService
import com.collicode.common.util.toJson
import com.collicode.tickety.infrastructure.event.dto.EventRequest
import com.collicode.tickety.infrastructure.event.repository.EventWriteRepository
import com.collicode.tickety.infrastructure.event.repository.model.EventWriteModel
import io.r2dbc.postgresql.codec.Json
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Service
class EventCreateUseCase(
    private val eventWriteRepository: EventWriteRepository,
    val idService: IdService
) : ActionWriteService<EventRequest, Unit> {
    override fun executeAction(request: EventRequest): Mono<Unit> {
        val eventWriteModel = EventWriteModel(
            recordId = idService.generateNewId(),
            title = request.title,
            description = request.description,
            date = request.date,
            location = (Json.of(toJson(request.location))),
            ticketTypes = (Json.of(toJson(request.ticketTypes))),
            totalCapacity = request.totalCapacity,
            organizationId = request.organizationId,
            createdAt = LocalDateTime.now(),
        )
        return eventWriteRepository.createEvent(eventWriteModel)
    }

    override fun logAction(request: EventRequest): Mono<Unit> {
        TODO("Not yet implemented")
    }
}