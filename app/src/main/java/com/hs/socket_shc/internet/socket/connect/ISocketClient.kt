package com.hs.socket_shc.internet.socket.connect

import com.hs.socket_shc.SocketConstants
import com.hs.socket_shc.internet.socket.connect.listener.SocketClientStatusChangeListener
import io.socket.client.Socket

interface ISocketClient{

    fun connect(uri : String): Socket

    fun disconnect()

    fun getConnectStatus(): SocketConstants.ConnectStatus

    fun registerStatusChangeListener(socketClientStatusChangeListener: SocketClientStatusChangeListener)

    fun unRegisterStatusChangeListener(socketClientStatusChangeListener: SocketClientStatusChangeListener)

}