# Event Ticketing System API Documentation

## Overview

RESTful API for an event ticketing system with M-Pesa payment integration built using Domain-Driven Design principles.

## Base URL

```
https://api.eventtickets.co.ke/v1
```

## Authentication

All authenticated endpoints require a Bearer token in the Authorization header:

```
Authorization: Bearer <jwt_token>
```

---

## 1. AUTHENTICATION & USER MANAGEMENT

### Auth Endpoints

```
POST   /api/auth/register              # Register new organizer
POST   /api/auth/login                 # Login organizer
POST   /api/auth/logout                # Logout organizer
POST   /api/auth/refresh               # Refresh JWT token
POST   /api/auth/forgot-password       # Request password reset
POST   /api/auth/reset-password        # Reset password with token
```

### User Management

```
GET    /api/users/profile              # Get current user profile
PUT    /api/users/profile              # Update user profile
PUT    /api/users/password             # Change password
PUT    /api/users/mpesa-settings       # Update M-Pesa payment settings
GET    /api/users/mpesa-settings       # Get M-Pesa payment settings
DELETE /api/users/account              # Delete user account
```

---

## 2. EVENT MANAGEMENT

### Event CRUD Operations

```
POST   /api/events                     # Create new event
GET    /api/events                     # Get all public events (with filters)
GET    /api/events/{eventId}           # Get event details with availability
PUT    /api/events/{eventId}           # Update event details
DELETE /api/events/{eventId}           # Delete/cancel event
PATCH  /api/events/{eventId}/status    # Activate/deactivate event
```

### Event Query & Search

```
GET    /api/events/search              # Search events with filters
GET    /api/events/featured            # Get featured events
GET    /api/events/popular             # Get popular events
GET    /api/events/upcoming            # Get upcoming events
GET    /api/events/by-city/{city}      # Get events by city
GET    /api/events/by-organizer        # Get events by current organizer
```

### Event Query Parameters

```
?city=nairobi                          # Filter by city
?category=music                        # Filter by category
?date_from=2024-01-01                  # Filter from date
?date_to=2024-12-31                    # Filter to date
?price_min=100                         # Minimum price filter
?price_max=5000                        # Maximum price filter
?page=1&size=20                        # Pagination
?sort=date&order=asc                   # Sorting
```

### Ticket Type Management

```
POST   /api/events/{eventId}/ticket-types     # Add ticket type to event
PUT    /api/events/{eventId}/ticket-types/{ticketTypeId}  # Update ticket type
DELETE /api/events/{eventId}/ticket-types/{ticketTypeId}  # Remove ticket type
GET    /api/events/{eventId}/ticket-types     # Get all ticket types for event
GET    /api/events/{eventId}/availability     # Get real-time availability
```

---

## 3. BOOKING & TICKETING

### Booking Operations

```
POST   /api/bookings                   # Create new booking (initiate payment)
GET    /api/bookings/{bookingId}       # Get booking details
PUT    /api/bookings/{bookingId}       # Update booking details
DELETE /api/bookings/{bookingId}       # Cancel booking
```

### Booking Queries

```
GET    /api/bookings                   # Get user's bookings
GET    /api/bookings/by-email/{email}  # Get bookings by customer email
GET    /api/bookings/by-event/{eventId} # Get bookings for specific event
GET    /api/bookings/pending           # Get pending payment bookings
GET    /api/bookings/confirmed         # Get confirmed bookings
```

### Ticket Operations

```
GET    /api/tickets/{ticketId}         # Get ticket details
PUT    /api/tickets/{ticketId}/transfer # Transfer ticket to another person
GET    /api/tickets/by-booking/{bookingId} # Get tickets for booking
POST   /api/tickets/validate           # Validate ticket QR code
POST   /api/tickets/scan               # Scan ticket at event entry
GET    /api/tickets/download/{ticketId} # Download ticket PDF
```

---

## 4. PAYMENT INTEGRATION (M-PESA)

### Payment Operations

```
POST   /api/payments/mpesa/initiate    # Initiate M-Pesa STK Push
POST   /api/payments/mpesa/callback    # M-Pesa callback endpoint (webhook)
GET    /api/payments/mpesa/status/{checkoutId} # Check payment status
POST   /api/payments/mpesa/refund      # Initiate refund
```

### Payment Queries

```
GET    /api/payments/{bookingId}       # Get payment details for booking
GET    /api/payments/transactions      # Get payment transaction history
GET    /api/payments/reconciliation    # Payment reconciliation report
```

---

## 5. NOTIFICATIONS

### Email Notifications

```
POST   /api/notifications/email        # Send custom email notification
GET    /api/notifications/email/templates # Get email templates
POST   /api/notifications/email/tickets    # Send ticket confirmation email
```

### SMS Notifications

```
POST   /api/notifications/sms          # Send SMS notification
POST   /api/notifications/sms/payment-reminder # Send payment reminder SMS
```

---

## 6. ANALYTICS & REPORTING

### Event Analytics

```
GET    /api/analytics/events/{eventId} # Get event analytics
GET    /api/analytics/events/{eventId}/sales    # Get sales analytics
GET    /api/analytics/events/{eventId}/attendance # Get attendance analytics
GET    /api/analytics/dashboard        # Get organizer dashboard data
```

### Sales Reports

```
GET    /api/reports/sales              # Get sales report
GET    /api/reports/sales/export       # Export sales report (CSV/PDF)
GET    /api/reports/bookings           # Get bookings report
GET    /api/reports/revenue            # Get revenue report
GET    /api/reports/customers          # Get customer report
```

---

## 7. ADMIN OPERATIONS

### System Management

```
GET    /api/admin/system/health        # System health check
GET    /api/admin/system/metrics       # System metrics
POST   /api/admin/system/maintenance   # Enable/disable maintenance mode
```

### User Management (Admin)

```
GET    /api/admin/users                # Get all users
GET    /api/admin/users/{userId}       # Get user details
PUT    /api/admin/users/{userId}/status # Enable/disable user
DELETE /api/admin/users/{userId}       # Delete user account
```

### Content Management

```
GET    /api/admin/events               # Get all events (admin view)
PUT    /api/admin/events/{eventId}/feature # Feature/unfeature event
DELETE /api/admin/events/{eventId}     # Admin delete event
GET    /api/admin/bookings             # Get all bookings
GET    /api/admin/payments             # Get all payments
```

---

## 8. WEBHOOK ENDPOINTS

### External Integrations

```
POST   /api/webhooks/mpesa/callback    # M-Pesa payment callback
POST   /api/webhooks/mpesa/timeout     # M-Pesa timeout callback
POST   /api/webhooks/mpesa/result      # M-Pesa transaction result
```

---

## Example Request/Response Formats

### Create Event Request

```json
POST /api/events
{
  "title": "Nairobi Music Festival 2024",
  "description": "Annual music festival featuring local and international artists",
  "date": "2024-06-15T18:00:00",
  "location": {
    "venue": "Uhuru Gardens",
    "address": "Langata Road",
    "city": "Nairobi",
    "coordinates": {
      "lat": -1.3028,
      "lng": 36.7856
    }
  },
  "ticketTypes": [
    {
      "name": "Regular",
      "price": 2500.00,
      "quantity": 1000,
      "maxPerOrder": 4
    },
    {
      "name": "VIP",
      "price": 5000.00,
      "quantity": 200,
      "maxPerOrder": 2
    }
  ],
  "totalCapacity": 1200
}
```

### Book Tickets Request

```json
POST /api/bookings
{
  "eventId": "550e8400-e29b-41d4-a716-446655440000",
  "ticketTypeId": "550e8400-e29b-41d4-a716-446655440001",
  "quantity": 2,
  "customerInfo": {
    "name": "John Doe",
    "email": "john.doe@email.com",
    "phone": "254712345678"
  }
}
```

### M-Pesa Payment Response

```json
{
  "bookingId": "550e8400-e29b-41d4-a716-446655440002",
  "totalAmount": 5000.00,
  "mpesaCheckoutRequestId": "ws_CO_DMZ_123456789_12345678",
  "mpesaMerchantRequestId": "29115-34620561-1",
  "customerMessage": "Please complete payment on your phone"
}
```

---

## HTTP Status Codes

| Status | Description                                                  |
|--------|--------------------------------------------------------------|
| 200    | OK - Request successful                                      |
| 201    | Created - Resource created successfully                      |
| 400    | Bad Request - Invalid request data                           |
| 401    | Unauthorized - Authentication required                       |
| 403    | Forbidden - Insufficient permissions                         |
| 404    | Not Found - Resource not found                               |
| 409    | Conflict - Resource conflict (e.g., event capacity exceeded) |
| 422    | Unprocessable Entity - Validation errors                     |
| 500    | Internal Server Error - Server error                         |

---

## Rate Limiting

- Authentication endpoints: 5 requests per minute
- Payment endpoints: 10 requests per minute
- General API endpoints: 100 requests per minute
- Search endpoints: 50 requests per minute

---

## Pagination

All list endpoints support pagination:

```
?page=1&size=20&sort=createdAt&order=desc
```

Response includes pagination metadata:

```json
{
  "data": [
    ...
  ],
  "pagination": {
    "page": 1,
    "size": 20,
    "total": 150,
    "totalPages": 8,
    "hasNext": true,
    "hasPrevious": false
  }
}
```