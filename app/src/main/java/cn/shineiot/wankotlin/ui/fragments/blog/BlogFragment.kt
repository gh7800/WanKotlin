package cn.shineiot.wankotlin.ui.fragments.blog

import cn.shineiot.base.mvp.BaseFragment
import cn.shineiot.wankotlin.R

class BlogFragment :BaseFragment<BlogView,BlogPresenter>() ,BlogView{
    override fun initPresenter(): BlogPresenter? {
        return BlogPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_blog
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