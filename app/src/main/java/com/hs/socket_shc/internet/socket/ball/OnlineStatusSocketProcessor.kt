package com.hs.socket_shc.internet.socket.ball

import android.util.Log
import com.hs.socket_shc.Constants
import io.socket.client.Socket
import org.json.JSONException
import org.json.JSONObject
import java.util.*

private const val TAG = "ClientOnlineProcessor"
class ClientOnlineProcessor(private val listener: SocketDataResponse): SocketProcessor {

    override fun on(socket: Socket?) {
        socket?.on(Constants.BALL_ONLINE) {
                Log.i(TAG, "Constants.BALL_ONLINE ${Arrays.toString(it)}")
                onResponse(Constants.BALL_ONLINE, it)
            }
            ?.on(Constants.CLIENT_ENTER) {
                Log.i(TAG, "Constants.CLIENT_ENTER ${Arrays.toString(it)}")
                onResponse(Constants.CLIENT_ENTER, it)
            }
            ?.on(Constants.CLIENT_LEAVE){
                Log.i(TAG, "Constants.CLIENT_LEAVE ${Arrays.toString(it)}")
                onResponse(Constants.CLIENT_LEAVE, it)
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