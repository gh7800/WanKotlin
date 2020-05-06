package cn.shineiot.wankotlin.ui.fragments.navigation

import android.view.View
import cn.shineiot.base.mvp.BaseFragment
import cn.shineiot.base.utils.SPutils
import cn.shineiot.wankotlin.App
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.utils.Constants
import io.reactivex.internal.util.NotificationLite.getValue
import kotlinx.android.synthetic.main.fragment_navigation.*

class NavigationFragment : BaseFragment<NavigationView,NavigationPresenter>() ,NavigationView{
    override fun initPresenter(): NavigationPresenter? {
        return NavigationPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_navigation
    }

    override fun initView() {
        val sPutils = SPutils()
        id_text.text = sPutils.getValue(Constants.ID,0).toString()
        username_text.text = sPutils.getValue(Constants.USERNAME,"").toString()
        publicname_text.text = sPutils.getString(Constants.PUBLIC_NAME)

        logout.setOnClickListener {
            presenter?.logout()
        }
    }

    override fun lazyLoad() {
    }

    override fun SuccessData() {
        //退出应用
        App.logoutApp()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
    }

    override fun errorMsg(msg: String?) {
    }
}