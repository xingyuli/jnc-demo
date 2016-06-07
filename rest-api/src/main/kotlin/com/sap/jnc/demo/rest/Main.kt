package com.sap.jnc.demo.rest

import com.google.gson.Gson
import spark.Spark
import spark.Spark.halt

fun main(args: Array<String>) {
    clients["jnc"] = Client("jnc", "123456")

    Spark.post("/api/client_auth") { req, res ->
        val body = Gson().fromJson(req.body(), Map::class.java)
        val clientid = body["clientid"]?.toString()
        val clientsecret = body["clientsecret"]?.toString()

        if (clientid == null || clientsecret == null) {
            halt(400)
            return@post Unit
        }

        if (!authenticateClient(clientid, clientsecret)) {
            halt(401)
            return@post Unit
        }

        res.header("Content-Type", "application/json;charset=utf8")
        return@post Gson().toJson(generateToken(clientid))
    }

    Spark.post("/api/erp/order") { req, res ->
        val input = Gson().fromJson(req.body(), OrderApiInput::class.java)

        if (input.token == null || !checkToken(input.token)) {
            halt(403)
            return@post Unit
        }

        res.header("Content-Type", "application/json;charset=utf8")
        return@post Gson().toJson(createOrder(input))
    }
}

interface ApiInput {
    val token: String?
}

class OrderApiInput(override val token: String?) : ApiInput {
    val dealer: String? = null
    val externalOrderNo: String? = null
    val projectNo: String? = null
    val boxNo: String? = null
    val materialNo: String? = null
}
