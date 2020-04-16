package com.hs.websocket_shc.base

interface IPresenter<T> {

    fun attachView(t: T)

    fun destroyView()
}