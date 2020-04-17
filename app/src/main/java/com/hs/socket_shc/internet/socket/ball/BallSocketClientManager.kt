package com.hs.socket_shc.internet.socket.ball

import com.hs.socket_shc.Constants
import com.hs.socket_shc.internet.socket.connect.SocketClient
import com.hs.socket_shc.internet.socket.connect.listener.SocketClientStatusChangeListener

private const val TAG = "BallSocketClientManager"
object BallSocketClientManager : SocketClientStatusChangeListener{


    private var orgId: String = "client001"
    private var mSocketClient: SocketClient? = null
    private var mSocketClientStatusChangeListener: SocketClientStatusChangeListener? = null

    private val socketProcessors = ArrayList<SocketProcessor>()

    init {
        socketProcessors.add(ClientOnlineProcessor())
    }

    fun connect(uri: String, listener: SocketClientStatusChangeListener){

        mSocketClientStatusChangeListener = listener

        mSocketClient = SocketClient()
        mSocketClient?.registerStatusChangeListener(this)
        val socket = mSocketClient?.connect(uri)

        for (processor in socketProcessors){
            processor.execute(socket)
        }
    }

    override fun onConnectStatusChange(connectStatus: Constants.ConnectStatus) {
        mSocketClientStatusChangeListener?.onConnectStatusChange(connectStatus)
    }

    fun disconnect() {
        if(getConnectStatus() != Constants.ConnectStatus.DISCONNECTED) mSocketClient?.disconnect()
    }

    fun getConnectStatus(): Constants.ConnectStatus{
        return mSocketClient?.getConnectStatus() ?: Constants.ConnectStatus.DISCONNECTED
    }


}