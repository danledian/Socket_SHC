package com.hs.socket_shc.base

open class BasePresenter<T: IView> : IPresenter<T> {

    protected var rootView: T? = null

    override fun attachView(t: T) {
        rootView = t
    }

    override fun destroyView() {
        rootView = null
    }



}