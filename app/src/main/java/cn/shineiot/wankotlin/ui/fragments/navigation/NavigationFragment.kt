package cn.shineiot.wankotlin.ui.fragments.navigation

import cn.shineiot.base.mvp.BaseFragment
import cn.shineiot.wankotlin.R

class NavigationFragment : BaseFragment<NavigationView,NavigationPresenter>() ,NavigationView{
    override fun initPresenter(): NavigationPresenter? {
        return NavigationPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_navigation
    }

    override fun initView() {
    }

    override fun lazyLoad() {
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
    }

    override fun errorMsg(msg: String?) {
    }
}