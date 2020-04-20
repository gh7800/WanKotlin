package cn.shineiot.wankotlin.ui.fragments.home.project

import android.content.Intent
import android.view.View
import cn.shineiot.base.mvp.BaseFragment
import cn.shineiot.base.utils.ToastUtils
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.Public
import cn.shineiot.wankotlin.ui.WebViewActivity
import kotlinx.android.synthetic.main.fragment_project.*

class ProjectFragment : BaseFragment<ProjectView,ProjectPresenter>(),ProjectView {
    private val page :Int = 1
    private lateinit var adapter : ProjectAdapter

    override fun initPresenter(): ProjectPresenter? {
        return ProjectPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun initView() {
        adapter = ProjectAdapter(R.layout.item_project_fragment)
        projectRecyclerView.adapter = adapter

        adapter.setOnItemClickListener { adapterProject, view, position ->
            val item = adapterProject.getItem(position) as Public
            val intent = Intent()
            intent.setClass(context,WebViewActivity::class.java)
            intent.putExtra("path",item.link)
            startActivity(intent)
         }
        presenter?.getProject(page)
    }

    override fun lazyLoad() {

    }

    override fun successData(data: List<Public>) {
        adapter.addData(data)
    }


    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    override fun errorMsg(msg: String?) {
        ToastUtils.DEFAULT.show(msg)
    }
}