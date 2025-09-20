package com.collicode.tickety.infrastructure.event.service.usecase

import com.collicode.common.service.actions.ActionWriteService
import com.collicode.tickety.infrastructure.event.dto.ApiDeleteRequest
import com.collicode.tickety.infrastructure.event.repository.impl.EventWriteRepositoryImpl
import com.sun.jdi.request.EventRequest
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class EventDeleteUseCase (
    private val eventWriteRepository: EventWriteRepositoryImpl
): ActionWriteService<ApiDeleteRequest, Unit>{
    override fun executeAction(request: ApiDeleteRequest): Mono<Unit> {
        return eventWriteRepository.deleteEvent(request.eventId)
    }

    override fun logAction(request: ApiDeleteRequest): Mono<Unit> {
        TODO("Not yet implemented")
    }


}