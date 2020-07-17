package cn.shineiot.wankotlin.ui.fragments.navigation

import cn.shineiot.base.mvp.AbstractObserver
import cn.shineiot.base.mvp.BasePresenter
import cn.shineiot.wankotlin.bean.User

import cn.shineiot.wankotlin.http.HttpClient

class NavigationPresenter : BasePresenter<NavigationView>(){
    fun logout(){
        HttpClient.logout(object : AbstractObserver<User>(){
            override fun requestSuccess(t: User?) {
                mRootView?.SuccessData()
            }

            override fun requestFaild(error: String?) {
                mRootView?.errorMsg(error)
            }

        })
    }
}