package com.collicode.tickety.infrastructure.event.api

import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.RequestPredicates.*
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Service
class EventApiResource {
    @Bean(name = ["eventApiRoute"])
    fun routes(eventApiHandler: EventApiHandler): RouterFunction<ServerResponse> {
        return RouterFunctions.route(
            POST(BASE_ROUTE),
            eventApiHandler::createEvent

        ).andRoute(
            PUT(EVENT_BY_ID)
                .and(accept(MediaType.APPLICATION_JSON)),
            eventApiHandler::updateEvent
        ).andRoute(
            DELETE(EVENT_BY_ID)
                .and(accept(MediaType.APPLICATION_JSON)),
            eventApiHandler::deleteEvent
        ).andRoute(
            DELETE(BASE_ROUTE)
                .and(accept(MediaType.APPLICATION_JSON)),
            eventApiHandler::fetchAllEvents
        )
    }

}