package com.collicode.tickety.infrastructure.event.repository.impl

import com.collicode.common.util.asUnit
import com.collicode.tickety.infrastructure.event.repository.EventWriteRepository
import com.collicode.tickety.infrastructure.event.repository.model.EventWriteModel
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
open class EventWriteRepositoryImpl (
     val r2dbcEntityTemplate: R2dbcEntityTemplate
): EventWriteRepository {
    override fun createEvent(model: EventWriteModel): Mono<Unit> {
        return r2dbcEntityTemplate.insert(model)
            .asUnit()
    }

}