package com.collicode.tickety.infrastructure.event.api

const val BASE_ROUTE = "/api/v1/events"


const val EVENT_BY_ID = "$BASE_ROUTE/{eventId}"

const val RESOURCE_NAME = "resource_name"
const val CREATE = "CREATE"
const val UPDATE = "UPDATE"
const val DELETE = "DELETE"