package cn.shineiot.wankotlin.ui.fragments.home.project

import cn.shineiot.base.mvp.BaseListResult
import cn.shineiot.base.mvp.BasePresenter
import cn.shineiot.base.mvp.BaseResult
import cn.shineiot.wankotlin.bean.PageEntity
import cn.shineiot.wankotlin.bean.Project
import cn.shineiot.wankotlin.http.RetrofitManager
import rx.Observable
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ProjectPresenter :BasePresenter<ProjectView>() {
    fun getProject(page:Int){
        RetrofitManager.service.getProject(page)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseResult<PageEntity>>() {
                override fun onNext(result: BaseResult<PageEntity>?) {
                    if(result?.errorCode == 0){
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
}