package com.hs.socket_shc

class Constants {

    companion object{

        const val BALL_ONLINE: String = "ball_online"//球在线
        const val CLIENT_ENTER: String = "clientEnter"//客户进入
        const val CLIENT_LEAVE: String = "clientLeave"//客户离开

        const val CLIENT_ID = "clientId"//客户ID
        const val BALL_AHI_UPDATE = "BALL_AHI_UPDATE"
    }

    enum class ConnectStatus{

        DISCONNECTED, CONNECTING, CONNECTED
    }

}