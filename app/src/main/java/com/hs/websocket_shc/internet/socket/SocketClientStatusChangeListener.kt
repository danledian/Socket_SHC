package com.hs.websocket_shc.internet.socket

import com.hs.websocket_shc.Constants

interface SocketClientStatusChangeListener {

    fun onConnectStatusChange(connectStatus: Constants.ConnectStatus)
}