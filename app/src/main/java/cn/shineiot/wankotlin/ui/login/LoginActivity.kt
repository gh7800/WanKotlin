package cn.shineiot.wankotlin.ui.login

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.TextUtils
import cn.shineiot.base.mvp.BaseMVPActivity
import cn.shineiot.base.mvp.BaseResult
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.base.utils.SPutils
import cn.shineiot.base.utils.ToastUtils
import cn.shineiot.wankotlin.App.Companion.context
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.User
import cn.shineiot.wankotlin.http.HttpClient
import cn.shineiot.wankotlin.ui.main.MainActivity
import cn.shineiot.wankotlin.utils.Constants
import cn.shineiot.wankotlin.utils.MDialogUtil
import com.maning.mndialoglibrary.MProgressDialog
import com.maning.mndialoglibrary.config.MDialogConfig
import com.maning.mndialoglibrary.listeners.OnDialogDismissListener
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.*
import retrofit2.Response

/**
 * login
 */
class LoginActivity : BaseMVPActivity<LoginView.View, LoginPresenter>(), LoginView.View {

    private val sPutils = SPutils()
    private lateinit var job: Job

    //handler的创建方法
    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg != null) {
                LogUtil.e(msg)
            }
        }
    }

    override fun initPresenter(): LoginPresenter {
        return LoginPresenter()
    }


    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    private lateinit var result: Response<BaseResult<User>>

    override fun initView() {

        val name = sPutils.getValue(Constants.USERNAME,"");
        LogUtil.e("---$name")
        if(TextUtils.isEmpty(name as CharSequence?)){
//            username.text = name
        }

        login.isEnabled = true  //直接用id调用view的属性 （需要插件 kotlin-android-extensions 的支持）

        login.setOnClickListener {
            closeKeyBord(username, this)
            val username: String = username.text.toString().trim()
            val password: String = password.text.toString().trim()
            when {
                TextUtils.isEmpty(username) -> {
                    ToastUtils.DEFAULT.show("请输入用户名");
                }
                TextUtils.isEmpty(password) -> {
                    ToastUtils.DEFAULT.show("请输入密码");
                }
                else -> {
                    showLoading()

                    //presenter?.login(username, password)
                    //presenter?.loginBy(username, password)

                    job = launch {
                        //LogUtil.e(Thread.currentThread().id)
                        withContext(Dispatchers.IO) {
                            //LogUtil.e(Thread.currentThread().id)
                            delay(10000)
                            result = HttpClient.service.loginBy(username, password).execute()
                        }
                        LogUtil.e(result)
                        if (result.isSuccessful) {
                            if (result.body()?.errorCode == 0) {
                                result.body()?.data?.let { successData(it) }
                            } else {
                                errorMsg(result.body()?.errorMsg)
                            }
                        } else {
                            errorMsg(result.message())
                        }
                    }
                }
            }
        }
    }

    override fun successData(user: User) {
        dismissLoading()
        sPutils.saveValue(Constants.USERNAME, user.username)
        sPutils.saveValue(Constants.PUBLIC_NAME, user.publicName)
        sPutils.saveValue(Constants.ID, user.id)

        val name = sPutils.getValue(Constants.USERNAME,"");
        LogUtil.e("$====$name")

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }

    override fun showLoading() {

        MDialogUtil.showLoading(mContext,"登录中", OnDialogDismissListener {
                if (job.isActive) {
                    job.cancel() //取消登录请求
                }else{
                    LogUtil.e("dialog关闭")
                }
            })
    }

    override fun dismissLoading() {
        mHandler.postDelayed({
            MDialogUtil.hideDialog()
        }, 1000)

    }

    override fun errorMsg(msg: String?) {
        dismissLoading()
        ToastUtils.DEFAULT.show(msg)
    }
}