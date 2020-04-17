package com.hs.socket_shc.internet.socket.ball

import org.json.JSONObject

interface SocketDataListener {

    fun ballOnline(json: JSONObject?)

    fun clientEnter(json: JSONObject?)

    fun clientLeave(json: JSONObject?)
}