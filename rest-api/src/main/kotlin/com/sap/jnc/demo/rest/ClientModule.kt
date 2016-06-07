package com.sap.jnc.demo.rest

import java.util.*

class Client(val id: String, val secret: String)
internal val clients = HashMap<String, Client>()

fun authenticateClient(id: String, secret: String) = clients.filterValues { it.id == id && it.secret == secret }.isNotEmpty()