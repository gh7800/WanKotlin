package cn.shineiot.wankotlin.ui.login

import cn.shineiot.base.mvp.BasePresenter
import cn.shineiot.base.mvp.BaseResult
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.wankotlin.bean.User
import cn.shineiot.wankotlin.http.RetrofitManager
import cn.shineiot.base.utils.ToastUtils
import cn.shineiot.wankotlin.http.AbstractObserver
import cn.shineiot.wankotlin.http.HttpClient
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LoginPresenter : BasePresenter<LoginView.View>() {

    fun login(username: String, password: String) {

        HttpClient.login(username, password, object : AbstractObserver<User>() {
            override fun requestSuccess(user: User) {
                mRootView?.successData(user)
            }

            override fun requestFaild(error: String?) {
                mRootView?.errorMsg(error)
            }

            override fun onCompleted() {

            }

        })

        /*RetrofitManager.service.login(username, password)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseResult<User>>() {
                override fun onNext(result: BaseResult<User>?) {
                    val code = result?.errorCode
                    if (code == 0) {
                        val user: User = result.data
                        mRootView?.successData(user)
                    } else {
                        ToastUtils.DEFAULT.show(result?.errorMsg)
                    }
                }

                override fun onCompleted() {
                }

                override fun onError(e: Throwable?) {
                    if (e != null) {
                        LogUtil.e(e.message.toString())
                    }
                    mRootView?.dismissLoading()
                }

            })*/

    }


}
