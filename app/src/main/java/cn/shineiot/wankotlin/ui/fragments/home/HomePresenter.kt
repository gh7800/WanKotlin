package cn.shineiot.wankotlin.ui.fragments.home

import cn.shineiot.base.mvp.BasePresenter
import cn.shineiot.wankotlin.bean.Banner
import cn.shineiot.wankotlin.http.AbstractObserver
import cn.shineiot.wankotlin.http.HttpClient

class HomePresenter : BasePresenter<HomeView>() {
    fun getBanner() {
        HttpClient.getBanner(object : AbstractObserver<List<Banner>>(){
            override fun requestSuccess(t: List<Banner>) {
                mRootView?.successBanner(t)
            }

            override fun requestFaild(error: String?) {
                mRootView?.errorMsg(error)
            }

            override fun onCompleted() {

            }

        })
    }
}