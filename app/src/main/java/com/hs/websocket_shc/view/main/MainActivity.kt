package com.hs.websocket_shc.view.main

import android.view.View
import com.hs.websocket_shc.Constants
import com.hs.websocket_shc.R
import com.hs.websocket_shc.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


private const val TAG = "MainActivity"
class MainActivity : BaseActivity(), MainInterface.IMainView{


    private val presenter: MainInterface.IMainPresenter by lazy {
        MainPresenter()
    }

    override fun initView() {
        presenter.attachView(this)
    }

    fun onViewClicked(view: View){
        when(view.id){
            R.id.bt_connect -> presenter.changeConnect()
        }
    }

    override fun showButtonConnectStatus(status: Constants.ConnectStatus) {
        bt_connect.text = status.name
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

}
