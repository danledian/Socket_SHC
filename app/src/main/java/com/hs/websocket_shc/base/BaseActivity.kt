package com.hs.websocket_shc.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open abstract class BaseActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
    }

    abstract fun initView()

    abstract fun getLayoutId(): Int


}