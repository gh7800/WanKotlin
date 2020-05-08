package cn.shineiot.wankotlin.ui.fragments.home.public

import cn.shineiot.base.mvp.BasePresenter
import cn.shineiot.wankotlin.bean.PageEntity
import cn.shineiot.wankotlin.bean.Public
import cn.shineiot.wankotlin.http.AbstractObserver
import cn.shineiot.wankotlin.http.HttpClient

class PublicPresenter :BasePresenter<PublicView>() {
    fun getPublic(page:Int){
        HttpClient.getNewPublic(page,object : AbstractObserver<PageEntity>(){
            override fun requestSuccess(t: PageEntity) {
                mRootView?.successData(t.datas)
            }

            override fun requestFaild(error: String?) {
                mRootView?.errorMsg(error)
            }

        })
    }

    fun collect(id:Int){
        HttpClient.collect(id,object : AbstractObserver<Public>(){
            override fun requestSuccess(public: Public?) {
                mRootView?.SuccessCollect()
            }

            override fun requestFaild(error: String?) {
                mRootView?.errorMsg(error)
            }

        })
    }

    fun unCollect(id:Int){
        HttpClient.unCollect(id,object : AbstractObserver<Public>(){
            override fun requestSuccess(t: Public?) {
                mRootView?.SuccessUnCollect()
            }

            override fun requestFaild(error: String?) {
                mRootView?.errorMsg(error)
            }

        })
    }
}