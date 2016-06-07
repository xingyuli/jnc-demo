package com.sap.jnc.demo.rest

import java.util.*

class Token(clientid: String, val nonce: String, val timestamp: Long) {
    val token = "$clientid$nonce$timestamp".hashCode().toString()
}
internal val tokens = HashMap<String, Token>()

fun generateToken(clientid: String) = Token(clientid, System.currentTimeMillis().hashCode().toString(), System.currentTimeMillis()).apply {
    tokens[clientid] = this
}

fun checkToken(token: String): Boolean {
    return tokens.filterValues { it.token == token }.isNotEmpty()
}