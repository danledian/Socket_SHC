package com.hs.socket_shc.internet.socket

import android.util.Log
import com.hs.socket_shc.Constants
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject
import java.util.*

private const val TAG = "SocketClient"
class SocketClient {


    private var socket: Socket? = null
    private var socketClientStatusChangeListener: SocketClientStatusChangeListener? = null
    private var connectStatus: Constants.ConnectStatus =
        Constants.ConnectStatus.DISCONNECTED

    public fun connect(uri : String){

        socket = IO.socket(uri)
        socket!!.on(Socket.EVENT_CONNECT) {
                Log.i(TAG, "connected")
                updateConnectStatus(Constants.ConnectStatus.CONNECTED)
            }
            .on(Socket.EVENT_CONNECTING){
                Log.i(TAG, Socket.EVENT_CONNECTING)
                updateConnectStatus(Constants.ConnectStatus.CONNECTING)
            }
            .on(Socket.EVENT_CONNECT_ERROR){
                Log.i(TAG, Arrays.toString(it))
                updateConnectStatus(Constants.ConnectStatus.DISCONNECTED)
            }
            .on(Constants.BALL_ONLINE) {
                Log.i(TAG, "Constants.BALL_ONLINE ${Arrays.toString(it)}")

                if(it != null && it.isNotEmpty()){
                    val json = JSONObject(it[0].toString())
                    val count = json.optInt("client001")

                    Log.i(TAG, "client001 count $count")
                }
            }
            .on(Constants.CLIENT_ENTER) {
                Log.i(TAG, "Constants.CLIENT_ENTER ${Arrays.toString(it)}")

            }
            .on(Socket.EVENT_DISCONNECT) {
                Log.i(TAG, Socket.EVENT_DISCONNECT)
                updateConnectStatus(Constants.ConnectStatus.DISCONNECTED)
            }
        socket?.connect()
        Log.i(TAG, "after connect")
    }

    public fun disconnect(){
        socket?.disconnect()
        socket = null
    }

    public fun getConnectStatus(): Constants.ConnectStatus {
        return connectStatus
    }

    private fun updateConnectStatus(connectStatus: Constants.ConnectStatus){
        if(this.connectStatus == connectStatus){
            return
        }
        this.connectStatus = connectStatus
        socketClientStatusChangeListener?.onConnectStatusChange(connectStatus)
    }

    public fun registerStatusChangeListener(socketClientStatusChangeListener: SocketClientStatusChangeListener){
        this.socketClientStatusChangeListener = socketClientStatusChangeListener
    }

    public fun unRegisterStatusChangeListener(socketClientStatusChangeListener: SocketClientStatusChangeListener){
        this.socketClientStatusChangeListener = null
    }

//    val sslContext = SSLContext.getInstance("TLS")
//    sslContext.init(null, arrayOf<TrustManager>(SSLSocket.SSLTrustManager()), java.security.SecureRandom())
//
//    val okHttpClient = OkHttpClient.Builder()
////            .hostnameVerifier{ hostname, session -> true }
//        .hostnameVerifier(OkHostnameVerifier.INSTANCE)
//        .connectTimeout(30, TimeUnit.SECONDS)
//        .sslSocketFactory(sslContext.socketFactory, SSLSocket.SSLTrustManager())
////            .sslSocketFactory(SSLSocket.getSSLSocketFactory())
//        .build()
//
//    IO.setDefaultOkHttpWebSocketFactory(okHttpClient)
//    IO.setDefaultOkHttpCallFactory(okHttpClient)
//
//    val opts = IO.Options()
//    opts.callFactory = okHttpClient
//    opts.webSocketFactory = okHttpClient

}