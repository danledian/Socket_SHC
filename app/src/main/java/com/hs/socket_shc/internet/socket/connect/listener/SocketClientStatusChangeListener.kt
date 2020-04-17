package com.hs.socket_shc.internet.socket.connect.listener

import com.hs.socket_shc.SocketConstants

interface SocketClientStatusChangeListener {

    fun onConnectStatusChange(connectStatus: SocketConstants.ConnectStatus)
}