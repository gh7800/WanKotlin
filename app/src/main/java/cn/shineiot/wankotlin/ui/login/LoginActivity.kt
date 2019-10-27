package cn.shineiot.wankotlin.ui.login

import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Switch
import android.widget.Toast
import cn.shineiot.base.mvp.BaseActivity
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.User
import cn.shineiot.wankotlin.utils.Constants
import kotlinx.android.synthetic.main.activity_login.*

/**
 * login
 */
class LoginActivity : BaseActivity<LoginView.View, LoginPresenter>(), LoginView.View {

    private val mPresenter by lazy { LoginPresenter() } //初始化的时候加载
    override fun initPresenter(): LoginPresenter? {
        return mPresenter
    }


    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun initData() {
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

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun successData(user: User) {
        LogUtil.e(user.username)
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
    }

}