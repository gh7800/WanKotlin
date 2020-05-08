package cn.shineiot.wankotlin.ui.login

import cn.shineiot.base.mvp.BasePresenter
import cn.shineiot.wankotlin.bean.User
import cn.shineiot.wankotlin.http.AbstractObserver
import cn.shineiot.wankotlin.http.HttpClient

class LoginPresenter : BasePresenter<LoginView.View>() {

    fun login(username: String, password: String) {

        HttpClient.login(username, password, object : AbstractObserver<User>() {
            override fun requestSuccess(user: User) {
                mRootView?.successData(user)
            }

            override fun requestFaild(error: String?) {
                mRootView?.errorMsg(error)
            }

        })

    }


}
