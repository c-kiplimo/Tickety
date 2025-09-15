package com.collicode.tickety.infrastructure.event.repository.impl

import com.collicode.tickety.infrastructure.event.repository.EventReadRepository
import com.collicode.tickety.infrastructure.event.repository.model.EventReadModel
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
class EventReadRepositoryImpl (
    val r2dbcEntityTemplate: R2dbcEntityTemplate
) : EventReadRepository {
    override fun fetchAllPublicEvents(queryMap: Map<String, String>): Flux<EventReadModel> {
        return r2dbcEntityTemplate.select(EventReadModel::class.java)
            .all()
    }
}