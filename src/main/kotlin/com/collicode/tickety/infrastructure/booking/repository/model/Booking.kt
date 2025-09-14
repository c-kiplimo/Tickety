package com.collicode.tickety.infrastructure.booking.repository.model

import com.collicode.tickety.infrastructure.booking.dto.CustomerInfo
import com.collicode.tickety.infrastructure.booking.dto.PaymentStatus
import com.collicode.tickety.infrastructure.booking.dto.Ticket
import java.time.LocalDateTime

data class BookingWriteModel(
    val recordId: Long,
    val eventId: Long,
    val ticketTypeId: Long,
    val quantity: Int,
    val customerInfo: CustomerInfo,
    val totalAmount: Double,
    val paymentStatus: PaymentStatus = PaymentStatus.PENDING,
    val mpesaCheckoutRequestId: String? = null,
    val mpesaMerchantRequestId: String? = null,
    val mpesaTransactionId: String? = null,
    val mpesaReceiptNumber: String? = null,
    val tickets: List<Ticket>,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)