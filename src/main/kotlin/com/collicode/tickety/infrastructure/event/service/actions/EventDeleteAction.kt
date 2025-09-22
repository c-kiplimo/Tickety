package com.collicode.tickety.infrastructure.event.service.actions

import com.collicode.common.dto.ActionResponse
import com.collicode.common.service.actions.ActionWorkFlowService
import com.collicode.tickety.infrastructure.event.dto.ApiDeleteRequest
import com.collicode.tickety.infrastructure.event.service.usecase.EventDeleteUseCase
import handleActionExecution
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class EventDeleteAction (
    private val useCase: EventDeleteUseCase
): ActionWorkFlowService<ApiDeleteRequest>{
    override fun validate(request: ApiDeleteRequest): Mono<ApiDeleteRequest> {
       return Mono.just(request)
    }

    override fun processRequest(request: ApiDeleteRequest): Mono<ActionResponse> {
       return handleActionExecution(
           input = request,
           validateInput = ::validate,
           executeAction = useCase::executeAction
       )
    }

}