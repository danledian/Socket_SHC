package com.hs.websocket_shc.view.main

import android.util.Log
import com.hs.websocket_shc.Constants
import com.hs.websocket_shc.base.BasePresenter
import com.hs.websocket_shc.internet.socket.SocketClient
import com.hs.websocket_shc.internet.socket.SocketClientStatusChangeListener

private const val TAG = "MainPresenter"
class MainPresenter: BasePresenter<MainInterface.IMainView>(), MainInterface.IMainPresenter, SocketClientStatusChangeListener {

    private var mSocketClient: SocketClient? = null

    override fun changeConnect(uri: String) {
        if(mSocketClient?.getConnectStatus() == Constants.ConnectStatus.CONNECTED){
            disconnect()
        }else{
            connect(uri)
        }
    }

    private fun connect(uri: String){
        Log.i(TAG, "connect")
        mSocketClient?.disconnect()

        mSocketClient = SocketClient()
        mSocketClient?.registerStatusChangeListener(this)
        mSocketClient?.connect(uri)
    }

    override fun onConnectStatusChange(connectStatus: Constants.ConnectStatus) {
        Log.i(TAG, "status change is ${connectStatus.name}")
        rootView?.showButtonConnectStatus(connectStatus)
    }

    override fun disconnect() {
        Log.i(TAG, "disconnect")
        mSocketClient?.disconnect()
    }


}