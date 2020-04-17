package com.hs.socket_shc.view.main

import com.hs.socket_shc.SocketConstants
import com.hs.socket_shc.base.IPresenter
import com.hs.socket_shc.base.IView

class MainInterface {

    interface IMainPresenter: IPresenter<IMainView> {

        fun changeConnect(uri: String = "http://192.168.1.65:3000/")

        fun disconnect()
    }

    interface IMainView: IView{

        fun showButtonConnectStatus(status: SocketConstants.ConnectStatus)
    }
}