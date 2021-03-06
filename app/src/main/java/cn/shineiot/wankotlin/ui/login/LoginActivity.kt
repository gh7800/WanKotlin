package cn.shineiot.wankotlin.ui.login

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.Editable
import android.text.TextUtils
import android.view.KeyEvent
import cn.shineiot.base.mvp.BaseMVPActivity
import cn.shineiot.base.mvp.BaseResult
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.base.utils.SharePreutils
import cn.shineiot.base.utils.ToastUtils
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.User
import cn.shineiot.wankotlin.ui.main.MainActivity
import cn.shineiot.wankotlin.utils.Constants
import cn.shineiot.wankotlin.utils.MDialogUtil
import com.maning.mndialoglibrary.listeners.OnDialogDismissListener
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Response

/**
 * login
 */
class LoginActivity : BaseMVPActivity<LoginView.View, LoginPresenter>(), LoginView.View {

    private val sPutils = SharePreutils()

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

    override fun initView() {

        val name = sPutils.getValue(Constants.USERNAME, "");
        LogUtil.e("---$name")
        if (TextUtils.isEmpty(name as CharSequence?)) {
//            username.text = name
            username.text = Editable.Factory.getInstance().newEditable(name)
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

                    presenter.login(username, password)

                }
            }
        }
    }

    override fun successData(user: User) {
        ToastUtils.DEFAULT.show("登录成功")
        username.postDelayed({
            dismissLoading()
            sPutils.saveValue(Constants.USERNAME, user.username)
            sPutils.saveValue(Constants.PUBLIC_NAME, user.publicName)
            sPutils.saveValue(Constants.ID, user.id)

            val name = sPutils.getValue(Constants.USERNAME, "");
            LogUtil.e("$====$name")

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },5000)

    }

    override fun showLoading() {

        MDialogUtil.showLoading(mContext, "登录中", OnDialogDismissListener {
            //presenter.cancelLogin()
        })
    }

    override fun dismissLoading() {
        MDialogUtil.hideDialog()

    }

    override fun errorMsg(msg: String?) {
        dismissLoading()
        ToastUtils.DEFAULT.show(msg)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }
}