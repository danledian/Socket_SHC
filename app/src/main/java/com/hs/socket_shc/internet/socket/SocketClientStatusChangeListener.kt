package com.hs.socket_shc.internet.socket

import com.hs.socket_shc.Constants

interface SocketClientStatusChangeListener {

    fun onConnectStatusChange(connectStatus: Constants.ConnectStatus)
}