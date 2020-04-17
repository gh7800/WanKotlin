package cn.shineiot.wankotlin.ui.fragments.home.project

import cn.shineiot.base.mvp.BaseFragment
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.Project

class ProjectFragment : BaseFragment<ProjectView,ProjectPresenter>(),ProjectView {
    private val page :Int = 1

    override fun initPresenter(): ProjectPresenter? {
        return ProjectPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun initView() {
        presenter?.getProject(page)
    }

    override fun lazyLoad() {

    }

    override fun successData(data: List<Project>) {
        LogUtil.e(data)
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    override fun errorMsg(msg: String?) {

    }
}