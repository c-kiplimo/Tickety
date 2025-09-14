package com.collicode.tickety.infrastructure.booking.dto

import java.time.LocalDateTime
import java.util.*


data class BookTicketsRequest(
    val eventId: Long,
    val ticketTypeId: Long,
    val quantity: Int,
    val customerInfo: CustomerInfo
)

data class CustomerInfo(
    val name: String,
    val email: String,
    val phone: String // M-Pesa registered phone number (format: 2547XXXXXXXX)
)

enum class PaymentStatus {
    PENDING, COMPLETED, FAILED, CANCELLED
}

data class Ticket(
    val id: UUID = UUID.randomUUID(),
    val ticketNumber: String,
    val qrCode: String,
    val isUsed: Boolean = false,
    val usedAt: LocalDateTime? = null,
    val scannedBy: UUID? = null
)

