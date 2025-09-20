package com.collicode.tickety.config

import com.collicode.common.service.IDGeneratorConfig
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@ConfigurationProperties(prefix = "id-info")
@Component
data class IDGenerator(
    override val datacenter: Long = 1,
    override val machineId: Long = 1
) : IDGeneratorConfig(
    datacenter, machineId
)