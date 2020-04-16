package com.hs.websocket_shc

public class Constants {

    companion object{
        val BALL_ONLINE: String = "ball_online"
        val CLIENT_ENTER: String = "clientEnter"
    }

    public enum class ConnectStatus{

        DISCONNECTED, CONNECTING, CONNECTED
    }

}