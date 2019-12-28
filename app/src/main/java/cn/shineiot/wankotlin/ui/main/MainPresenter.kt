package cn.shineiot.wankotlin.ui.main

import cn.shineiot.base.mvp.BaseListResult
import cn.shineiot.base.mvp.BasePresenter
import cn.shineiot.wankotlin.bean.Banner
import cn.shineiot.wankotlin.http.RetrofitManager
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainPresenter : BasePresenter<MainView>() {
    fun getBanner() {
        RetrofitManager.service.getBanner()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseListResult<Banner>>() {
                override fun onNext(result: BaseListResult<Banner>) {
                    mRootView?.successBanner(result.data)
                }

                override fun onError(e: Throwable) {
                    mRootView?.errorMsg(e.message)
                }

                override fun onCompleted() {

                }
            })
    }
}