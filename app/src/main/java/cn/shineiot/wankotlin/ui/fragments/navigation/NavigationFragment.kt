package cn.shineiot.wankotlin.ui.fragments.navigation

import android.content.Intent
import cn.shineiot.base.mvp.BaseMvpFragment
import cn.shineiot.base.utils.SharePreutils
import cn.shineiot.base.utils.ToastUtils
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.ui.login.LoginActivity
import cn.shineiot.wankotlin.utils.Constants
import kotlinx.android.synthetic.main.fragment_navigation.*

class NavigationFragment : BaseMvpFragment<NavigationView,NavigationPresenter>() ,NavigationView{
    private val sharePreutils :SharePreutils by lazy { SharePreutils() }

    override fun initPresenter(): NavigationPresenter? {
        return NavigationPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_navigation
    }

    override fun initView() {
        logout.setOnClickListener {
            logout.isEnabled = false
            presenter?.logout()
        }

        lazyLoad()
    }

    override fun lazyLoad() {
//        if(id_text.text.isEmpty()) {
            id_text.text = sharePreutils.getValue(Constants.ID, 0).toString()
            username_text.text = sharePreutils.getValue(Constants.USERNAME, "").toString()
            publicname_text.text = sharePreutils.getString(Constants.PUBLIC_NAME)
//        }
    }

    override fun SuccessData() {
        val sPutils = SharePreutils()
        sPutils.clear()

        val intent = Intent(activity,LoginActivity::class.java)
        startActivity(intent)

        //退出应用
        //App.logoutApp()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
    }

    override fun errorMsg(msg: String?) {
        logout.isEnabled = true
        ToastUtils.DEFAULT.show(msg)
    }
}