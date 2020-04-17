package com.hs.socket_shc.internet.socket.ball

import io.socket.client.Socket

interface SocketProcessor{

    fun execute(socket: Socket?)
}