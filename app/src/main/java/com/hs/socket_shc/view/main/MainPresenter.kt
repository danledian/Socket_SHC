package com.hs.socket_shc.view.main

import android.util.Log
import com.hs.socket_shc.Constants
import com.hs.socket_shc.base.BasePresenter
import com.hs.socket_shc.internet.socket.ball.BallSocketClientManager
import com.hs.socket_shc.internet.socket.connect.listener.SocketClientStatusChangeListener

private const val TAG = "MainPresenter"
class MainPresenter: BasePresenter<MainInterface.IMainView>(), MainInterface.IMainPresenter,
    SocketClientStatusChangeListener {

    override fun changeConnect(uri: String) {
        if(BallSocketClientManager.getConnectStatus() == Constants.ConnectStatus.CONNECTED){
            disconnect()
        }else{
            connect(uri)
        }
    }

    private fun connect(uri: String){
        Log.i(TAG, "connect")
        BallSocketClientManager.connect(uri, this)
    }

    override fun onConnectStatusChange(connectStatus: Constants.ConnectStatus) {
        Log.i(TAG, "status change is ${connectStatus.name}")
        rootView?.showButtonConnectStatus(connectStatus)
    }

    override fun disconnect() {
        Log.i(TAG, "disconnect")
        BallSocketClientManager.disconnect()
    }

}