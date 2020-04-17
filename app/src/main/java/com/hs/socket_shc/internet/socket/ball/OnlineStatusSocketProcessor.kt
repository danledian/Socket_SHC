package com.hs.socket_shc.internet.socket.ball

import android.text.TextUtils
import android.util.Log
import com.hs.socket_shc.Constants
import io.socket.client.Socket
import org.json.JSONObject
import java.util.*

private const val TAG = "ClientOnlineProcessor"
class ClientOnlineProcessor(): SocketProcessor {

    private var orgId: String? = null

    override fun execute(socket: Socket?) {
        socket?.on(Constants.BALL_ONLINE) {
                Log.i(TAG, "Constants.BALL_ONLINE ${Arrays.toString(it)}")
                if(it != null && it.isNotEmpty()){
                    val json = JSONObject(it[0].toString())
                    val count = json.optInt(orgId)
                    Log.i(TAG, "$orgId's count is $count")
                    if(count > 0){

                    }
                }
            }
            ?.on(Constants.CLIENT_ENTER) {
                Log.i(TAG, "Constants.CLIENT_ENTER ${Arrays.toString(it)}")
                if(it != null && it.isNotEmpty()){
                    val json = JSONObject(it[0].toString())
                    val clientId = json.optString(Constants.CLIENT_ID)
                    if (TextUtils.isEmpty(clientId) && clientId == orgId){

                    }
                }
            }
            ?.on(Constants.CLIENT_LEAVE){
                Log.i(TAG, "Constants.CLIENT_ENTER ${Arrays.toString(it)}")

            }
    }

}