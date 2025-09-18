package com.collicode.tickety.infrastructure.event.api

import com.collicode.tickety.infrastructure.event.api.DELETE
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.RequestPredicates.DELETE
import org.springframework.web.reactive.function.server.RequestPredicates.POST
import org.springframework.web.reactive.function.server.RequestPredicates.PUT
import org.springframework.web.reactive.function.server.RequestPredicates.accept
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse
import kotlin.math.PI

@Service
class EventApiResource {
    @Bean(name = ["eventApiRoute"])
    fun routes(eventApiHandler: EventApiHandler): RouterFunction<ServerResponse> {
        return RouterFunctions.route(
            POST(BASE_ROUTE),
            eventApiHandler::createEvent

        ).andRoute(
            PUT(UPDATE_EVENT)
                .and(accept(MediaType.APPLICATION_JSON)),
            eventApiHandler::updateEvent
        ).andRoute(
            DELETE(DELETE_EVENT)
                .and(accept(MediaType.APPLICATION_JSON)),
            eventApiHandler::deleteEvent
        )
    }

}