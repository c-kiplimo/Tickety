# Event Ticketing System with M-Pesa Integration - User Journey Documentation

## 1. Organizer Journey: Creating and Managing Events

### 1.1 Account Setup & Registration

1. User navigates to platform and selects **"Sign Up as Organizer"**
2. Provides email, name, phone number (**must be M-Pesa registered number**)
3. Sets up secure password
4. Configures M-Pesa payment details:
    - Business Short Code (Paybill or Till Number)
    - Account reference format for events
    - Uploads M-Pesa API credentials
5. Verifies email and phone number via OTP
6. Account activated and ready to create events

### 1.2 Event Creation Process

1. Logs into organizer dashboard
2. Clicks **"Create New Event"**
3. Fills event details:
    - Event title and description
    - Date and time
    - Location details (venue, address, city, optional coordinates)
    - Total capacity
4. Configures ticket types:
    - **Single Tickets**: Regular admission tickets
    - **Group Tickets**: Discounted tickets for groups (specifies group size)
    - Sets price, quantity available, and maximum per order
5. Reviews event summary and publishes
6. Event goes live and appears on public listing

### 1.3 Event Management

1. Views dashboard with event analytics:
    - Tickets sold vs available
    - Revenue generated
    - Booking trends
2. Monitors real-time bookings
3. Can pause ticket sales if needed
4. Downloads attendee lists
5. Views payment reconciliation reports

---

## 2. Attendee Journey: Discovering and Booking Events

### 2.1 Event Discovery

1. Browses public event listing page
2. Filters events by:
    - Date range
    - Location/city
    - Price range
    - Event category
3. Views event details page with:
    - Full description and images
    - Ticket types and prices
    - Real-time availability
    - Location map

### 2.2 Ticket Selection Process

1. Selects desired event
2. Chooses ticket type (Single or Group)
3. Selects quantity (respects max per order limits)
4. Views running total cost
5. Proceeds to checkout

### 2.3 Checkout and Payment

1. Enters attendee information:
    - Full name
    - Email address
    - Phone number (**must be M-Pesa registered**)
2. Reviews order summary:
    - Event details
    - Ticket quantities
    - Total amount
3. Initiates M-Pesa payment:
    - System generates STK push request
    - Attendee receives prompt on their phone
4. Completes payment on phone:
    - Enters M-Pesa PIN
    - Confirms payment
5. Waits for payment confirmation (**typically 10–30 seconds**)

### 2.4 Post-Booking Experience

1. Receives instant confirmation:
    - Email confirmation with booking details and tickets attached
    - SMS confirmation with summary and booking ID
2. Tickets include:
    - Unique QR code for each ticket
    - Event details
    - Booking reference number
    - M-Pesa transaction details
3. Can view booking in **"My Tickets"** section
4. Receives reminder **24 hours before event**

---

## 3. Payment Processing Journey (M-Pesa Integration)

### 3.1 Payment Initiation

1. System validates phone number format (**2547XXXXXXXX**)
2. Booking Service creates pending booking record
3. Payment Service generates STK push request:
    - Encrypts password using timestamp + passkey
    - Sets transaction type to `CustomerPayBillOnline`
    - Includes callback URL for payment confirmation
4. Request sent to M-Pesa API

### 3.2 Payment Execution

1. M-Pesa sends push notification to customer's phone
2. Customer enters M-Pesa PIN to authorize payment
3. M-Pesa processes transaction
4. M-Pesa sends callback to our system with result

### 3.3 Payment Confirmation

1. System receives callback from M-Pesa
2. Payment Service processes callback:
    - Checks result code (**0 = success, other = failure**)
    - Extracts transaction details from metadata
3. On **success**:
    - Updates booking status to `COMPLETED`
    - Stores M-Pesa receipt number
    - Generates tickets with QR codes
    - Triggers notification service
4. On **failure**:
    - Updates booking status to `FAILED`
    - Releases reserved tickets
    - Sends failure notification to attendee

---

## 4. Gatekeeper Journey: Event Day Operations

### 4.1 Setup and Preparation

1. Downloads scanner app or uses web portal
2. Logs in with gatekeeper credentials
3. Selects current event from assigned events list
4. App syncs latest ticket data for offline use

### 4.2 Ticket Validation Process

1. Attendee presents QR code (digital or printed)
2. Gatekeeper scans QR code using device camera
3. System validates QR code:
    - Checks cryptographic signature
    - Verifies ticket belongs to current event
    - Confirms ticket hasn't been used already
4. For **valid tickets**:
    - Marks ticket as used in system
    - Records timestamp and gatekeeper ID
    - Shows green checkmark and attendee name
5. For **invalid tickets**:
    - Shows red "X" with reason
    - Prevents entry

### 4.3 Handling Special Cases

1. **Duplicate scan detection**:
    - System shows warning `"Already scanned at [timestamp]"`
    - Prevents double entry
2. **Offline mode**:
    - Scans are cached locally
    - Synced when connection restored
3. **Manual entry**:
    - Fallback for damaged QR codes
    - Enter booking ID or phone number

---

## 5. Support and Issue Resolution Journey

### 5.1 Common Attendee Issues

**Payment Issues**:

- Didn't receive STK push → Resend payment request
- Payment deducted but no confirmation → Check with M-Pesa and manual verification
- Wrong amount deducted → Initiate refund process

**Ticket Issues**:

- Lost tickets → Resend via email/SMS
- Can't attend → Process refund based on policy

**Event Changes**:

- Date/venue changes → Notify all attendees, offer refund option

### 5.2 Organizer Support

**Technical Issues**:

- API integration problems
- Payment reconciliation discrepancies

**Event Management**:

- Capacity changes
- Emergency cancellations
- Reporting requirements

---

## 6. Analytics and Reporting Journey

### 6.1 Organizer Reports

**Sales Reports**:

- Tickets sold by type
- Revenue by day/week
- Capacity utilization

**Attendee Analytics**:

- Demographic information
- Booking patterns
- Repeat customers

**Financial Reports**:

- M-Pesa reconciliation
- Commission calculations
- Payout schedules

### 6.2 System-wide Analytics

**Platform Performance**:

- Total events created
- Successful bookings
- Payment success rates

**Geographic Distribution**:

- Events by location
- Attendee locations

**Peak Usage Times**:

- Booking patterns
- System load planning

---

This documentation outlines the complete user journey for all stakeholders in the **Event Ticketing System with M-Pesa
integration**, ensuring smooth operations from event creation to attendance validation.
