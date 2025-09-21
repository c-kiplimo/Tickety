package com.collicode.tickety.infrastructure.event.repository.model

import com.collicode.tickety.infrastructure.event.dto.Location
import com.collicode.tickety.infrastructure.event.dto.TicketType
import io.r2dbc.postgresql.codec.Json
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime


@Table("events")
data class EventWriteModel(
    val recordId: Long,
    val organizationId: Long,
    val title: String,
    val description: String,
    val date: LocalDateTime,
    val location: Json,
    val ticketTypes: Json,
    val totalCapacity: Int,
    val bookedCount: Int = 0,
    val isActive: Boolean = true,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    val auditInfo: Json? = null,
)

data class EventReadModel(
    val recordId: Long,
    val organizationId: Long,
    val title: String,
    val description: String,
    val date: LocalDateTime,
    val location: Location,
    val ticketTypes: List<TicketType>,
    val totalCapacity: Int,
    val bookedCount: Int = 0,
    val isActive: Boolean = true,
    val createdAt: LocalDateTime? = null,
)