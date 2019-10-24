package cn.shineiot.wankotlin.ui.login

import android.util.Log
import cn.shineiot.base.mvp.BasePresenter

class LoginPresenter : BasePresenter<LoginView.View>() {
    fun login(){
        Log.e("tag","login")
        mRootView?.SuccessData("登录成功")
        mRootView?.showLoading()
    }
}