package cn.shineiot.wankotlin.ui.fragments.blog

import cn.shineiot.base.mvp.BasePresenter
import cn.shineiot.base.mvp.BaseResult
import cn.shineiot.wankotlin.bean.PageEntity
import cn.shineiot.wankotlin.bean.Public
import cn.shineiot.wankotlin.http.AbstractObserver
import cn.shineiot.wankotlin.http.HttpClient
import cn.shineiot.wankotlin.http.RetrofitManager

class BlogPresenter : BasePresenter<BlogView>() {
    fun getMyCollect(page:Int){
        HttpClient.getMyCollect(page,object :AbstractObserver<PageEntity>(){
            override fun requestSuccess(t: PageEntity) {
                mRootView?.SuccessData(t)
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