package cn.shineiot.wankotlin.ui.fragments.home.project

import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.shineiot.base.mvp.BaseFragment
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.base.utils.ToastUtils
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.Public
import cn.shineiot.wankotlin.ui.PhotoActivity
import cn.shineiot.wankotlin.ui.WebViewActivity
import cn.shineiot.wankotlin.utils.Constants
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.module.BaseLoadMoreModule
import com.chad.library.adapter.base.module.LoadMoreModule
import kotlinx.android.synthetic.main.fragment_project.*

/**
 * 最新项目
 */
class ProjectFragment : BaseFragment<ProjectView, ProjectPresenter>(), ProjectView,
    SwipeRefreshLayout.OnRefreshListener,OnItemChildClickListener {
    private var page: Int = 1
    private lateinit var adapter: ProjectAdapter
    private lateinit var loadMoreModule : BaseLoadMoreModule

    private var handler = Handler {
        when (it.what) {

        }
        false
    }

    override fun initPresenter(): ProjectPresenter? {
        return ProjectPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun initView() {
        projectSwip.setColorSchemeColors(
            Color.rgb(33, 150, 243),
            Color.rgb(255, 193, 7),
            Color.rgb(0, 0, 0)
        )
        projectSwip.setOnRefreshListener(this)

        adapter = ProjectAdapter(R.layout.item_project_fragment)
        projectRecyclerView.adapter = adapter

        loadMoreModule = adapter.loadMoreModule
        loadMoreModule.isAutoLoadMore = true
        loadMoreModule.setOnLoadMoreListener(listener = {
            LogUtil.e("loadMore")
            presenter?.getProject(page)
        })

        adapter.setOnItemChildClickListener(this)
        adapter.addChildClickViewIds(R.id.project_image)

        adapter.setOnItemClickListener { adapterProject, view, position ->
            val item = adapterProject.getItem(position) as Public
            val intent = Intent()
            intent.setClass(context, WebViewActivity::class.java)
            intent.putExtra("path", item.link)
            startActivity(intent)
        }

        showLoading()
        presenter?.getProject(page)
    }

    override fun lazyLoad() {

    }

    override fun onRefresh() {
        page = 1
        presenter?.getProject(page)
    }

    override fun successData(data: List<Public>) {
        handler.postDelayed(Runnable {
            dismissLoading()
            adapter.addData(data)
            page++
        }, 2000)
    }


    override fun showLoading() {
        projectSwip.isRefreshing = true
    }

    override fun dismissLoading() {
        if (projectSwip.isRefreshing) {
            projectSwip.isRefreshing = false
        }
        if(page > 1){
            loadMoreModule.loadMoreComplete()
        }
    }

    override fun errorMsg(msg: String?) {
        dismissLoading()
        ToastUtils.DEFAULT.show(msg)
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        if(view.id == R.id.project_image){
            val public = adapter.getItem(position) as Public
            val intent = Intent(context,PhotoActivity::class.java)
            intent.putExtra(Constants.PATH,public.envelopePic)
            startActivity(intent)
        }
    }
}