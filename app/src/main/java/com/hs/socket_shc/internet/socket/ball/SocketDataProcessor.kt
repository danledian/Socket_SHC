package com.hs.socket_shc.internet.socket.ball

import android.util.Log
import com.hs.socket_shc.SocketConstants
import io.socket.client.Socket
import org.json.JSONException
import org.json.JSONObject
import java.util.*

private const val TAG = "SocketDataProcessor"
class SocketDataProcessor(private val listener: SocketDataResponse): SocketProcessor {

    override fun on(socket: Socket?) {
        socket?.on(SocketConstants.BALL_ONLINE) {
                Log.i(TAG, "SocketConstants.BALL_ONLINE ${Arrays.toString(it)}")
                onResponse(SocketConstants.BALL_ONLINE, it)
            }
            ?.on(SocketConstants.CLIENT_ENTER) {
                Log.i(TAG, "SocketConstants.CLIENT_ENTER ${Arrays.toString(it)}")
                onResponse(SocketConstants.CLIENT_ENTER, it)
            }
            ?.on(SocketConstants.CLIENT_LEAVE){
                Log.i(TAG, "SocketConstants.CLIENT_LEAVE ${Arrays.toString(it)}")
                onResponse(SocketConstants.CLIENT_LEAVE, it)
            }
    }

    private fun onResponse(action: String, it: Array<Any>?){
        try {
            val json = if(it != null && it.isNotEmpty()) JSONObject(it[0].toString()) else null
            listener.onResponse(action, json)
        }catch (exception: JSONException){
            listener.onResponse(action, null)
        }
    }

}