package cn.shineiot.wankotlin.ui.login

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.TextUtils
import android.view.KeyEvent
import android.widget.Toast
import cn.shineiot.base.mvp.BaseActivity
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.base.utils.SPutils
import cn.shineiot.base.utils.ToastUtils
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.User
import cn.shineiot.wankotlin.ui.main.MainActivity
import com.maning.mndialoglibrary.MProgressDialog
import kotlinx.android.synthetic.main.activity_login.*

/**
 * login
 */
class LoginActivity : BaseActivity<LoginView.View, LoginPresenter>(), LoginView.View {

    private val mPresenter by lazy { LoginPresenter() } //初始化的时候加载
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
        return mPresenter
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
            if (TextUtils.isEmpty(username)) {
                Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show()
            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show()
            } else {
                mPresenter.login(username, password)
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
//        mPresenter.detachView()
    }

    override fun successData(user: User) {
        LogUtil.e("success--$user")
//        ToastUtils.DEFAULT.show("登录成功")
        ToastUtils.newBuilder(R.layout.layout_toast,R.id.tv_toast).build().show("登录成功")

        var username = user.username
        sPutils.saveValue("username",username)
        var name = sPutils.getString("username")
        LogUtil.e("name--$name")
        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showLoading() {
        MProgressDialog.showProgress(this)
    }

    override fun dismissLoading() {
        mHandler.postDelayed({
            MProgressDialog.dismissProgress()
            var msg = Message()
            msg.obj = "message-1"
            mHandler.sendMessage(msg)
        }, 1000)

    }

}