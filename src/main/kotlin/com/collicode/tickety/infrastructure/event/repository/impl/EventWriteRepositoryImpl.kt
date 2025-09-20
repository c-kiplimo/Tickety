package com.collicode.tickety.infrastructure.event.repository.impl

import com.collicode.common.util.asUnit
import com.collicode.tickety.infrastructure.event.repository.EventWriteRepository
import com.collicode.tickety.infrastructure.event.repository.model.EventWriteModel
import com.collicode.tickety.infrastructure.event.service.Impl.EventQueryServiceImpl
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query.query
import org.springframework.data.relational.core.query.Update
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
open class EventWriteRepositoryImpl (
    val r2dbcEntityTemplate: R2dbcEntityTemplate,
): EventWriteRepository {
    override fun createEvent(model: EventWriteModel): Mono<Unit> {
        return r2dbcEntityTemplate.insert(model)
            .asUnit()
    }

    override fun updateEvent(model: EventWriteModel): Mono<Unit> {
        val query = query(Criteria.where("record_id").`is`(model.recordId))

        val update = Update.update("organization_id", model.organizationId)
            .set("title", model.title)
            .set("description", model.description)
            .set("date", model.date)
            .set("location", model.location)
            .set("ticketTypes", model.ticketTypes)
            .set("totalCapacity", model.totalCapacity)
            .set("bookedCount", model.bookedCount)
            .set("isActive", model.isActive)
            .set("updatedAt", model.updatedAt)

        return r2dbcEntityTemplate.update(query, update, EventWriteModel::class.java)
            .asUnit()
    }


    override fun deleteEvent(recordId: Long): Mono<Unit> {
        return r2dbcEntityTemplate.delete(EventWriteModel::class.java)
            .matching(query(Criteria.where("record_id").`is`(recordId)))
            .all()
            .asUnit()
    }


}