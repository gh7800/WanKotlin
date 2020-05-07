package cn.shineiot.wankotlin.ui.fragments.blog

import cn.shineiot.base.mvp.BasePresenter
import cn.shineiot.base.mvp.BaseResult
import cn.shineiot.wankotlin.bean.PageEntity
import cn.shineiot.wankotlin.bean.Public
import cn.shineiot.wankotlin.http.AbstractObserver
import cn.shineiot.wankotlin.http.HttpClient
import cn.shineiot.wankotlin.http.RetrofitManager
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class BlogPresenter : BasePresenter<BlogView>() {
    fun getMyCollect(page:Int){
        HttpClient.getMyCollect(page,object :AbstractObserver<PageEntity>(){
            override fun requestSuccess(t: PageEntity) {
                mRootView?.SuccessData(t)
            }

            override fun requestFaild(error: String?) {
                mRootView?.errorMsg(error)
            }

            override fun onCompleted() {

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

            override fun onCompleted() {

            }

        })
    }
}