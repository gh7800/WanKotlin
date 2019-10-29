package cn.shineiot.wankotlin.ui.main

import cn.shineiot.base.mvp.BaseActivity
import cn.shineiot.wankotlin.R

/**
 * 首页
 */
class MainActivity : BaseActivity<MainView,MainPresenter>(),MainView {

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initPresenter(): MainPresenter? {
        return MainPresenter()
    }

    override fun initView() {
        presenter?.getBanner()
    }
    override fun successData() {
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }


}
