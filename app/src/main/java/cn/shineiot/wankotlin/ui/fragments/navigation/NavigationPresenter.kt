package cn.shineiot.wankotlin.ui.fragments.navigation

import cn.shineiot.base.mvp.BasePresenter
import cn.shineiot.base.mvp.BaseResult
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.wankotlin.bean.User
import cn.shineiot.wankotlin.http.RetrofitManager
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class NavigationPresenter : BasePresenter<NavigationView>(){
    fun logout(){
        RetrofitManager.service.logout()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseResult<User>>(){
                override fun onNext(result: BaseResult<User>?) {
                    if(result?.errorCode == 0){
                        mRootView?.SuccessData()
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