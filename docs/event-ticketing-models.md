# Event Ticketing & M-Pesa Integration Models

```kotlin
import java.time.LocalDateTime
import java.util.UUID

// User Model for Organizers
data class User(
    val id: UUID = UUID.randomUUID(),
    val email: String,
    val hashedPassword: String,
    val name: String,
    val phone: String, // M-Pesa registered phone number
    val paymentDetails: MpesaPaymentDetails,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

// M-Pesa specific payment details
data class MpesaPaymentDetails(
    val businessShortCode: String, // Paybill or Till Number
    val accountReference: String, // Default account reference
    val transactionDesc: String = "Event Ticket Payment",
    val mpesaApiKey: String, // For Lipa Na M-Pesa API
    val mpesaPublicKey: String, // For encryption
    val passKey: String // Lipa Na M-Pesa online passkey
)

// Event Model
data class Event(
    val id: UUID = UUID.randomUUID(),
    val organizerId: UUID,
    val title: String,
    val description: String,
    val date: LocalDateTime,
    val location: Location,
    val ticketTypes: List<TicketType>,
    val totalCapacity: Int,
    val bookedCount: Int = 0,
    val isActive: Boolean = true,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
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

// Booking Model
data class Booking(
    val id: UUID = UUID.randomUUID(),
    val eventId: UUID,
    val ticketTypeId: UUID,
    val quantity: Int,
    val customerInfo: CustomerInfo,
    val totalAmount: Double,
    val paymentStatus: PaymentStatus = PaymentStatus.PENDING,
    val mpesaCheckoutRequestId: String? = null,
    val mpesaMerchantRequestId: String? = null,
    val mpesaTransactionId: String? = null,
    val mpesaReceiptNumber: String? = null,
    val tickets: List<Ticket>,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
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

// M-Pesa API Models
data class MpesaStkPushRequest(
    val businessShortCode: String,
    val password: String, // Base64 encoded timestamp + passkey
    val timestamp: String, // yyyyMMddHHmmss format
    val transactionType: String = "CustomerPayBillOnline",
    val amount: Double,
    val partyA: String, // Customer phone number
    val partyB: String, // Business short code
    val phoneNumber: String, // Customer phone number
    val callBackURL: String, // API endpoint for callback
    val accountReference: String, // Event ID or booking reference
    val transactionDesc: String
)

data class MpesaStkPushResponse(
    val merchantRequestID: String,
    val checkoutRequestID: String,
    val responseCode: String,
    val responseDescription: String,
    val customerMessage: String
)

data class MpesaCallback(
    val body: MpesaCallbackBody
)

data class MpesaCallbackBody(
    val stkCallback: MpesaStkCallback
)

data class MpesaStkCallback(
    val merchantRequestID: String,
    val checkoutRequestID: String,
    val resultCode: Int,
    val resultDesc: String,
    val callbackMetadata: MpesaCallbackMetadata?
)

data class MpesaCallbackMetadata(
    val item: List<MpesaCallbackItem>
)

data class MpesaCallbackItem(
    val name: String,
    val value: Any?
)

// API Request/Response Models
data class CreateEventRequest(
    val title: String,
    val description: String,
    val date: LocalDateTime,
    val location: Location,
    val ticketTypes: List<TicketType>,
    val totalCapacity: Int
)

data class BookTicketsRequest(
    val eventId: UUID,
    val ticketTypeId: UUID,
    val quantity: Int,
    val customerInfo: CustomerInfo
)

data class BookTicketsResponse(
    val bookingId: UUID,
    val totalAmount: Double,
    val mpesaCheckoutRequestId: String? = null,
    val mpesaMerchantRequestId: String? = null,
    val customerMessage: String // M-Pesa prompt message
)

data class ValidateTicketRequest(
    val ticketId: UUID,
    val qrCode: String
)

data class ValidateTicketResponse(
    val isValid: Boolean,
    val eventName: String? = null,
    val customerName: String? = null,
    val message: String
)

data class ScanTicketRequest(
    val ticketId: UUID,
    val qrCode: String,
    val scannerId: UUID
)

data class ScanTicketResponse(
    val success: Boolean,
    val eventName: String,
    val customerName: String,
    val message: String
)

// Notification Models
data class EmailNotification(
    val to: String,
    val subject: String,
    val body: String,
    val attachments: List<Attachment> = emptyList()
)

data class SMSNotification(
    val to: String, // Format: 2547XXXXXXXX
    val message: String
)

data class Attachment(
    val filename: String,
    val content: String, // Base64 encoded
    val contentType: String
)

// QR Code Generation Model
data class QRCodeData(
    val ticketId: UUID,
    val eventId: UUID,
    val bookingId: UUID,
    val secret: String
)
```
