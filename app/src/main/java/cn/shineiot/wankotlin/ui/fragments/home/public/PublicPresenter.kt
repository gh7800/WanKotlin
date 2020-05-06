package cn.shineiot.wankotlin.ui.fragments.home.public

import cn.shineiot.base.mvp.BaseListResult
import cn.shineiot.base.mvp.BasePresenter
import cn.shineiot.base.mvp.BaseResult
import cn.shineiot.wankotlin.bean.PageEntity
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
            .subscribe(object : Subscriber<BaseResult<PageEntity>>() {
                override fun onNext(result: BaseResult<PageEntity>?) {
                    if(result?.errorCode == 0) {
                        mRootView?.successData(result.data.datas)
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

    fun collect(id:Int){
        RetrofitManager.service.collect(id)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :Subscriber<BaseResult<Public>>(){
                override fun onNext(result: BaseResult<Public>?) {
                    if(result?.errorCode == 0){
                        mRootView?.SuccessCollect()
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

    fun unCollect(id:Int){
        RetrofitManager.service.uncollect(id)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :Subscriber<BaseResult<Public>>(){
                override fun onNext(result: BaseResult<Public>?) {
                    if(result?.errorCode == 0){
                        mRootView?.SuccessUnCollect()
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