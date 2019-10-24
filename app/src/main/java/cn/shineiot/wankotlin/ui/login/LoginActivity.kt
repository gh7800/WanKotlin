package cn.shineiot.wankotlin.ui.login

import android.util.Log
import cn.shineiot.base.mvp.BaseActivity
import cn.shineiot.wankotlin.R
import kotlinx.android.synthetic.main.activity_login.*

/**
 * login
 */
class LoginActivity : BaseActivity() ,LoginView.View{

    companion object{             //定义静态  = Static
        const val CODE : Int = 1 //定义常量   val=只读，var=读写
    }

    private val mPresenter by lazy { LoginPresenter() } //初始化的时候加载

    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun initData() {
    }

     override fun initView() {
         mPresenter.attachView(this)

        login.isEnabled = true  //直接用id调用view的属性 （需要插件 kotlin-android-extensions 的支持）

        login.setOnClickListener{
            closeKeyBord(username,this)
            var username = username.text
            Log.e("tag", "----$username")
            mPresenter.login()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun SuccessData(msg: String) {
        Log.e("tag",msg)
    }

    override fun showLoading() {
        Log.e("tag","showLoading")

    }

    override fun dismissLoading() {
    }

}