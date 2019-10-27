package cn.shineiot.wankotlin.ui.login

import cn.shineiot.base.mvp.BasePresenter
import cn.shineiot.base.mvp.BaseResult
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.wankotlin.bean.User
import cn.shineiot.wankotlin.http.RetrofitManager
import cn.shineiot.wankotlin.utils.ToastUtils
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LoginPresenter : BasePresenter<LoginView.View>() {

    fun login(username: String, password: String) {

        RetrofitManager.service.login(username, password)
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Subscriber<BaseResult<User>>() {
                override fun onStart() {
                    super.onStart()
                    mRootView?.showLoading()
                    ToastUtils.showToast("开始")
                }
                override fun onNext(result: BaseResult<User>?) {
                    var code = result?.error_Code
                    if(code == 0) {
                        var user: User = result?.data!!
                        mRootView?.successData(user)
                    }else{
                        mRootView?.dismissLoading()
                        ToastUtils.showToast(result?.error_Msg)
                    }
                }

                override fun onCompleted() {
                }

                override fun onError(e: Throwable?) {
                    if (e != null) {
                        LogUtil.e(e.message.toString())
                    }

                }

            } )

    }


}
