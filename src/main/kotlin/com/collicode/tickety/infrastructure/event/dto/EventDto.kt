package com.collicode.tickety.infrastructure.event.dto

import com.collicode.common.util.AuditInfo
import java.time.LocalDateTime
import java.util.*

data class EventRequest(
    val eventId: Long? = null,
    val organizationId: Long,
    val title: String,
    val description: String,
    val date: LocalDateTime,
    val location: Location,
    val ticketTypes: List<TicketType>,
    val totalCapacity: Int,
    val auditInfo: AuditInfo? = null
)

data class EventResponse(
    val eventId: Long? = null,
    val organizationId: Long,
    val title: String,
    val description: String,
    val date: LocalDateTime,
    val location: Location,
    val ticketTypes: List<TicketType>,
    val totalCapacity: Int,
    val auditInfo: AuditInfo? = null
)

data class ApiDeleteRequest(
    val eventId: Long,
    val organizationId: Long? = null,
)

data class Location(
    val venue: String,
    val address: String,
    val city: String,
    val coordinates: Coordinates? = null
)

data class Coordinates(
    val lat: Double,
    val lng: Double
)

/**
 * TicketType is now a plain data class.
 * - Use `groupSize = null` for single tickets.
 * - Provide a number for `groupSize` to represent group tickets.
 */
data class TicketType(
    val id: UUID? = UUID.randomUUID(),
    val name: String,
    val price: Double,
    val quantity: Int,
    val maxPerOrder: Int,
    val available: Boolean = true,
    val groupSize: Int? = null
)
