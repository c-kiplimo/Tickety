package com.collicode.tickety.infrastructure.event.dto

import java.time.LocalDateTime
import java.util.*

data class EventRequest(
    val eventId: Long? = null,
    val title: String,
    val description: String,
    val date: LocalDateTime,
    val location: String,
    val ticketTypes: List<TicketType>,
    val totalCapacity: Int
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

sealed class TicketType(
    open val id: UUID,
    open val name: String,
    open val price: Double,
    open val quantity: Int,
    open val maxPerOrder: Int,
    open val available: Boolean
) {
    data class SingleTicket(
        override val id: UUID = UUID.randomUUID(),
        override val name: String,
        override val price: Double,
        override val quantity: Int,
        override val maxPerOrder: Int,
        override val available: Boolean = true
    ) : TicketType(id, name, price, quantity, maxPerOrder, available)

    data class GroupTicket(
        override val id: UUID = UUID.randomUUID(),
        override val name: String,
        override val price: Double,
        override val quantity: Int,
        override val maxPerOrder: Int,
        val groupSize: Int,
        override val available: Boolean = true
    ) : TicketType(id, name, price, quantity, maxPerOrder, available)
}