package com.collicode.tickety.infrastructure.event.repository.impl

import com.collicode.common.util.QueryUtil
import com.collicode.tickety.infrastructure.event.repository.EventReadRepository
import com.collicode.tickety.infrastructure.event.repository.model.EventReadModel
import org.springframework.data.domain.Sort
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Query
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
class EventReadRepositoryImpl(
    private val r2dbcEntityTemplate: R2dbcEntityTemplate
) : EventReadRepository {

    override fun fetchAllEvents(queryMap: Map<String, String>): Flux<EventReadModel> {
        val allowedFields = setOf("recordId", "organizationId", "title", "description", "date", "isActive")
        val criteria = QueryUtil.createCriteria(queryMap, allowedFields)
        val pageRequest = QueryUtil.pageRequest(queryMap)

        return r2dbcEntityTemplate.select(
            Query.query(criteria)
                .with(pageRequest)
                .sort(Sort.by(Sort.Direction.DESC, "record_id")),
            EventReadModel::class.java
        )
    }
}
