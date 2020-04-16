package com.hs.socket_shc.base

interface IPresenter<T> {

    fun attachView(t: T)

    fun destroyView()
}