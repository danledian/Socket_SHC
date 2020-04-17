package com.hs.socket_shc.internet.socket.connect.listener

import com.hs.socket_shc.Constants

interface SocketClientStatusChangeListener {

    fun onConnectStatusChange(connectStatus: Constants.ConnectStatus)
}