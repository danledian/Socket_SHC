package com.hs.socket_shc.view.main

import android.util.Log
import com.hs.socket_shc.SocketConstants
import com.hs.socket_shc.SocketConstants.Companion.BALL_AHI_UPDATE
import com.hs.socket_shc.SocketConstants.Companion.BALL_ONLINE
import com.hs.socket_shc.SocketConstants.Companion.CLIENT_ID
import com.hs.socket_shc.base.BasePresenter
import com.hs.socket_shc.internet.socket.ball.BallSocketClientManager
import com.hs.socket_shc.internet.socket.ball.SocketDataListener
import com.hs.socket_shc.internet.socket.connect.listener.SocketClientStatusChangeListener
import org.json.JSONObject
import java.util.*

private const val TAG = "MainPresenter"
class MainPresenter: BasePresenter<MainInterface.IMainView>(), MainInterface.IMainPresenter,
    SocketClientStatusChangeListener {

    private val orgId: String = "client001"

    private var timer: Timer? = null
    private var timerTask: TimerTask? = null
    private var isSend: Boolean = false

    override fun changeConnect(uri: String) {
        if(BallSocketClientManager.getConnectStatus() == SocketConstants.ConnectStatus.CONNECTED){
            disconnect()
        }else{
            connect(uri)
        }
    }

    private fun connect(uri: String){
        Log.i(TAG, "connect")
        BallSocketClientManager.connect(uri, this)
        BallSocketClientManager.setOnlineActionListener(object : SocketDataListener{

            override fun ballOnline(json: JSONObject?) {
                checkClientExist(json)
            }

            override fun clientEnter(json: JSONObject?) {
                if (orgId == json?.optString(CLIENT_ID)){
                    startSendData()
                }
            }

            override fun clientLeave(json: JSONObject?) {
                checkClientExist(json)
            }

        })
    }

    private fun checkClientExist(json: JSONObject?){
        if(json !=null && json.optInt(orgId) > 0)
            startSendData()
        else
            stopSendData()
    }

    private fun startSendData(){
        if(isSend){
            return
        }
        Log.i(TAG, "start send data")
        isSend = true
        timer = Timer()
        timerTask = object: TimerTask(){
            override fun run() {
                if(BallSocketClientManager.getConnectStatus() == SocketConstants.ConnectStatus.CONNECTED){
                    BallSocketClientManager.getSocket()?.emit(BALL_AHI_UPDATE, "{\"AHI\"${Random().nextInt(100)}}")
                }
            }
        }
        timer?.schedule(timerTask, 0, 1000)
    }

    private fun stopSendData(){
        Log.i(TAG, "stop send data")
        isSend = false
        timer?.cancel()
        timer = null
    }

    override fun onConnectStatusChange(connectStatus: SocketConstants.ConnectStatus) {
        Log.i(TAG, "status change is ${connectStatus.name}")
        rootView?.showButtonConnectStatus(connectStatus)
        if(connectStatus == SocketConstants.ConnectStatus.CONNECTED){
            BallSocketClientManager.getSocket()?.emit(BALL_ONLINE)
        }
    }

    override fun disconnect() {
        Log.i(TAG, "disconnect")
        BallSocketClientManager.disconnect()
    }

}