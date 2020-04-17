package com.hs.socket_shc.internet.socket.ball

import org.json.JSONObject

interface SocketDataResponse {

    fun onResponse(action: String, json: JSONObject?)

}