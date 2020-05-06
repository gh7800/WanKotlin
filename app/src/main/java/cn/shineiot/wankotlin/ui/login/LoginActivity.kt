package cn.shineiot.wankotlin.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.TextUtils
import android.widget.Toast
import cn.shineiot.base.mvp.BaseMVPActivity
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.base.utils.SPutils
import cn.shineiot.base.utils.ToastUtils
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.User
import cn.shineiot.wankotlin.ui.main.MainActivity
import cn.shineiot.wankotlin.utils.Constants
import com.maning.mndialoglibrary.MProgressDialog
import kotlinx.android.synthetic.main.activity_login.*

/**
 * login
 */
class LoginActivity : BaseMVPActivity<LoginView.View, LoginPresenter>(), LoginView.View {

    private val sPutils by lazy { SPutils() }

    //handler的创建方法
    private val mHandler: Handler = object :Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg != null) {
                LogUtil.e(msg)
            }
        }
    }

    override fun initPresenter(): LoginPresenter? {
        return LoginPresenter()
    }


    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {

        login.isEnabled = true  //直接用id调用view的属性 （需要插件 kotlin-android-extensions 的支持）

        login.setOnClickListener {
            closeKeyBord(username, this)
            val username: String = username.text.toString()
            val password: String = password.text.toString()
            when {
                TextUtils.isEmpty(username) -> {
                    Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(password) -> {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    showLoading()
                    presenter?.login(username, password)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun successData(user: User) {
        dismissLoading()
        sPutils.saveValue(Constants.USERNAME,user.username)
        sPutils.saveValue(Constants.PUBLIC_NAME,user.publicName)
        sPutils.saveValue(Constants.ID,user.id)

        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()

    }

    override fun showLoading() {
        MProgressDialog.showProgress(mContext)
    }

    override fun dismissLoading() {
        mHandler.postDelayed({
            MProgressDialog.dismissProgress()
        }, 1000)

    }

    override fun errorMsg(msg: String?) {
        dismissLoading()
        ToastUtils.DEFAULT.show(msg)
    }

}