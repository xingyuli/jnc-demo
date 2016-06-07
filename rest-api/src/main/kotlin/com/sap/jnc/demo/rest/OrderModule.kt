package com.sap.jnc.demo.rest

import java.util.*
import java.util.concurrent.atomic.AtomicLong

class Order(val id: Long,
            val tradeNo: String,
            val dealer: String,
            val externalOrderNo: String,
            val projectNo: String?,
            val boxNo: String?,
            val materialNo: String?)

internal val orders = HashMap<Long, Order>()
private val autoincrement_primary_key = AtomicLong()
private val tradeno_generator = AtomicLong()

fun createOrder(input: OrderApiInput): Order {
    val result = with(input) {
        return@with Order(autoincrement_primary_key.incrementAndGet(), "DEMO${tradeno_generator.incrementAndGet()}", dealer!!, externalOrderNo!!, projectNo, boxNo, materialNo)
    }
    return result.apply { orders[id] = this }
}
