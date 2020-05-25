package cn.shineiot.wankotlin.ui.login

import cn.shineiot.base.mvp.BasePresenter
import cn.shineiot.base.mvp.BaseResult
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.wankotlin.bean.User
import cn.shineiot.wankotlin.http.AbstractObserver
import cn.shineiot.wankotlin.http.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter : BasePresenter<LoginView.View>() {

    fun login(username: String, password: String) {

        HttpClient.login(username, password, object : AbstractObserver<User>() {
            override fun requestSuccess(user: User) {
                mRootView?.successData(user)
            }

            override fun requestFaild(error: String?) {
                mRootView?.errorMsg(error)
            }

        })

    }


    fun loginBy(username: String, password: String) {

        lateinit var result: Response<BaseResult<User>>
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                result = HttpClient.service.loginBy(username, password).execute()
            }
            LogUtil.e(result)
            if (result.isSuccessful) {
                if (result.body()?.errorCode == 0) {
                    result.body()?.data?.let { mRootView?.successData(it) }
                }else{
                    mRootView?.errorMsg(result.body()?.errorMsg)
                }
            } else {
                mRootView?.errorMsg(result.message())
            }
        }

        /*HttpClient.service.loginBy(username, password).enqueue(object : Callback<BaseResult<User>> {
            override fun onFailure(call: Call<BaseResult<User>>, t: Throwable) {
                mRootView?.errorMsg(t.message)
            }

            override fun onResponse(
                call: Call<BaseResult<User>>,
                response: Response<BaseResult<User>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let { mRootView?.successData(it) }
                }
            }

        })*/
    }

}









