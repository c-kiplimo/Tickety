package com.collicode.tickety.infrastructure.event.service.actions

import com.collicode.common.dto.ActionResponse
import com.collicode.common.service.actions.ActionWorkFlowService
import com.collicode.tickety.infrastructure.event.service.usecase.EventUpdateUseCase
import com.sun.jdi.request.EventRequest
import handleActionExecution
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class EventUpdateAction (
    private val useCase: EventUpdateUseCase,
): ActionWorkFlowService< EventRequest>  {
    override fun validate(request: EventRequest): Mono<EventRequest> {
        TODO("Not yet implemented")
    }

    override fun processRequest(request: EventRequest): Mono<ActionResponse> {
        return handleActionExecution(
            input = request,
            validateInput = ::validate,
            executeAction = useCase::executeAction
        )
    }


}