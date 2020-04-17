package com.hs.socket_shc.internet.socket.ball

import android.util.Log
import com.hs.socket_shc.Constants
import com.hs.socket_shc.Constants.Companion.BALL_ONLINE
import com.hs.socket_shc.Constants.Companion.CLIENT_ENTER
import com.hs.socket_shc.Constants.Companion.CLIENT_LEAVE
import com.hs.socket_shc.internet.socket.connect.SocketClient
import com.hs.socket_shc.internet.socket.connect.listener.SocketClientStatusChangeListener
import io.socket.client.Socket
import org.json.JSONObject

private const val TAG = "BallSocketClientManager"
object BallSocketClientManager : SocketClientStatusChangeListener, SocketDataResponse{


    private var mSocketClient: SocketClient? = null
    private var mSocket: Socket? = null
    private var mSocketClientStatusChangeListener: SocketClientStatusChangeListener? = null
    private var mSocketDataListener: SocketDataListener? = null
    private var mOnlineStatusProcessor: ClientOnlineProcessor? = null

    init {
        mOnlineStatusProcessor = ClientOnlineProcessor(this)
    }

    fun connect(uri: String, listener: SocketClientStatusChangeListener){
        Log.i(TAG, "connect $uri")
        mSocketClientStatusChangeListener = listener

        mSocketClient = SocketClient()
        mSocketClient?.registerStatusChangeListener(this)
        mSocket = mSocketClient?.connect(uri)
        mOnlineStatusProcessor?.on(mSocket)
    }

    public fun getSocket(): Socket?{
        return mSocket
    }

    override fun onConnectStatusChange(connectStatus: Constants.ConnectStatus) {
        Log.i(TAG, "connect status is ${connectStatus.name}")
        mSocketClientStatusChangeListener?.onConnectStatusChange(connectStatus)
    }

    fun disconnect() {
        Log.i(TAG, "disconnect")
        if(getConnectStatus() != Constants.ConnectStatus.DISCONNECTED) mSocketClient?.disconnect()
        mSocket = null
    }

    fun getConnectStatus(): Constants.ConnectStatus{
        return mSocketClient?.getConnectStatus() ?: Constants.ConnectStatus.DISCONNECTED
    }

    fun setOnlineActionListener(socketDataListener: SocketDataListener){
        this.mSocketDataListener = socketDataListener
    }

    override fun onResponse(action: String, json: JSONObject?) {
        when(action){
            BALL_ONLINE -> mSocketDataListener?.ballOnline(json)
            CLIENT_ENTER -> mSocketDataListener?.clientEnter(json)
            CLIENT_LEAVE -> mSocketDataListener?.clientLeave(json)
        }
    }

}