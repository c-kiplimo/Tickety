package com.collicode.tickety.infrastructure.event.api

import com.collicode.common.api.wrapRequestWithBodyInApiResponse
import com.collicode.common.dto.ApiRequest
import com.collicode.common.util.toObject

import com.collicode.tickety.infrastructure.event.dto.EventRequest
import com.collicode.tickety.infrastructure.event.service.actions.EventCreateAction
import com.collicode.tickety.infrastructure.event.service.actions.EventUpdateAction
import com.google.gson.reflect.TypeToken
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Service
class EventApiHandler (
    val eventCreateAction: EventCreateAction,
    val eventUpdateAction: EventUpdateAction,
){
    fun createEvent(serverRequest: ServerRequest): Mono<ServerResponse> {
        return wrapRequestWithBodyInApiResponse(
            resource = RESOURCE_NAME,
            action = CREATE,
            serverRequest = serverRequest,
        ){ requestBody, auditInfo ->
            val apiRequestType = object : TypeToken<ApiRequest<EventRequest>>() {}.type
            val request: ApiRequest<EventRequest> =
               toObject(requestBody, apiRequestType)

            eventCreateAction.processRequest(
                request.payload.copy(
                    auditInfo = auditInfo,
                )
            )
        }
    }

    fun updateEvent(serverRequest: ServerRequest): Mono<ServerResponse> {
        return wrapRequestWithBodyInApiResponse(
            resource = RESOURCE_NAME,
            action = UPDATE,
            serverRequest = serverRequest,
        ){requestBody, auditInfo ->
            val apiRequestType = object : TypeToken<ApiRequest<EventRequest>>() {}.type
            val request: ApiRequest<EventRequest> =
                JsonHelper.toObject(requestBody, apiRequestType)

            val eventId: Long = serverRequest.pathVariable("id").toLong()
            eventUpdateAction.processRequest(
                request.payload.copy(eventId = eventId, auditInfo = auditInfo)
            )
        }
    }

    fun deleteEvent(serverRequest: ServerRequest): Mono<ServerResponse> {
        return wrapRequestWithBodyInApiResponse(
            resource = RESOURCE_NAME,
            action = DELETE,
            serverRequest = serverRequest,
        ){serveInRequest, _ ->
            val eventId: Long = serveInRequest.pathVariable

        }
    }
}