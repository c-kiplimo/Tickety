package com.collicode.tickety.infrastructure.event.service.actions

import com.collicode.common.dto.ActionResponse
import com.collicode.common.service.actions.ActionWorkFlowService
import com.collicode.tickety.infrastructure.event.dto.EventRequest
import com.collicode.tickety.infrastructure.event.service.usecase.EventCreateUseCase
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class EventCreateAction(
    private val useCase: EventCreateUseCase,
): ActionWorkFlowService<EventRequest> {
    override fun validate(request: EventRequest): Mono<EventRequest> {
        TODO("Not yet implemented")
    }

    override fun processRequest(request: EventRequest): Mono<ActionResponse> {
        return handleApprovalExecution(
            input = request,
            validate = ::validate,
            performAction = useCase::executeAction
        )
    }
}