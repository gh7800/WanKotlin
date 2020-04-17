package cn.shineiot.wankotlin.ui.fragments.home.public

import cn.shineiot.base.mvp.BaseListResult
import cn.shineiot.base.mvp.BasePresenter
import cn.shineiot.wankotlin.bean.Public
import cn.shineiot.wankotlin.http.RetrofitManager
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PublicPresenter :BasePresenter<PublicView>() {
    fun getPublic(page:Int){
        RetrofitManager.service.getPublic(page)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseListResult<Public>>() {
                override fun onNext(result: BaseListResult<Public>?) {
                    if(result?.errorCode == 0) {
                        mRootView?.successData(result.data)
                    }else{
                        mRootView?.errorMsg(result?.errorMsg)
                    }
                }

                override fun onCompleted() {
                }

                override fun onError(e: Throwable?) {
                    mRootView?.errorMsg(e?.message)
                }

            })
    }
}