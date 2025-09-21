package com.collicode.tickety.infrastructure.event.service.actions

import com.collicode.common.dto.ActionResponse
import com.collicode.common.service.actions.ActionWorkFlowService
import com.collicode.tickety.infrastructure.event.dto.EventRequest
import com.collicode.tickety.infrastructure.event.service.usecase.EventCreateUseCase
import handleActionExecution
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class EventCreateAction(
    val useCase: EventCreateUseCase,
) : ActionWorkFlowService<EventRequest> {
    override fun validate(request: EventRequest): Mono<EventRequest> {
        return Mono.just(request)
    }

    override fun processRequest(request: EventRequest): Mono<ActionResponse> {
        return handleActionExecution(
            input = request,
            validateInput = ::validate,
            executeAction = useCase::executeAction
        )
    }
}