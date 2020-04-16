package com.hs.websocket_shc.view.main

import com.hs.websocket_shc.Constants
import com.hs.websocket_shc.base.IPresenter
import com.hs.websocket_shc.base.IView

private const val TAG = "MainViewPresenter"
class MainInterface {

    interface IMainPresenter: IPresenter<IMainView> {

        fun changeConnect(uri: String = "http://192.168.4.189:3000/")

        fun disconnect()
    }

    interface IMainView: IView{

        fun showButtonConnectStatus(status: Constants.ConnectStatus)
    }
}