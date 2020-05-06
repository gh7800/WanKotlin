package cn.shineiot.wankotlin.ui.fragments.blog

import cn.shineiot.base.mvp.BasePresenter
import cn.shineiot.base.mvp.BaseResult
import cn.shineiot.wankotlin.bean.PageEntity
import cn.shineiot.wankotlin.http.RetrofitManager
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class BlogPresenter : BasePresenter<BlogView>() {
    fun getMyCollect(page:Int){
        RetrofitManager.service.getMyCollect(page)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseResult<PageEntity>>() {
                override fun onNext(result: BaseResult<PageEntity>?) {
                    if(result?.errorCode == 0){
                        mRootView?.SuccessData(result.data)
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