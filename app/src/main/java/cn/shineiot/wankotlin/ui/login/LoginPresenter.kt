package cn.shineiot.wankotlin.ui.login

import cn.shineiot.base.mvp.BasePresenter
import cn.shineiot.base.mvp.BaseResult
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.wankotlin.bean.User
import cn.shineiot.wankotlin.http.RetrofitManager
import cn.shineiot.base.utils.ToastUtils
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LoginPresenter : BasePresenter<LoginView.View>() {

    fun login(username: String, password: String) {

        RetrofitManager.service.login(username, password)
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseResult<User>>() {
                override fun onStart() {
                    super.onStart()
                    mRootView?.showLoading()
                }

                override fun onNext(result: BaseResult<User>?) {
                    var code = result?.errorCode
                    if (code == 0) {
                        LogUtil.e("11111111")
                        var user: User = result?.data!!
                        mRootView?.successData(user)
                    } else {
                        LogUtil.e("11111111222222222222222")

                        ToastUtils.DEFAULT.show(result?.errorMsg)
                    }
                    mRootView?.dismissLoading()
                }

                override fun onCompleted() {
                }

                override fun onError(e: Throwable?) {
                    if (e != null) {
                        LogUtil.e(e.message.toString())
                    }
                    mRootView?.dismissLoading()
                }

            })

    }


}
