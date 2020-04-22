package cn.shineiot.wankotlin.ui.fragments.home.public

import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.shineiot.base.mvp.BaseFragment
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.base.utils.ToastUtils
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.Public
import cn.shineiot.wankotlin.ui.WebViewActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.module.BaseLoadMoreModule
import kotlinx.android.synthetic.main.fragment_public.*

class PublicFragment : BaseFragment<PublicView, PublicPresenter>(), PublicView, OnItemClickListener,
    SwipeRefreshLayout.OnRefreshListener {

    private var page: Int = 1   //第x页
    private lateinit var adapter: PublicAdapter
    private lateinit var loadMoreModule : BaseLoadMoreModule

    private var handle = Handler {
        if (it.what == 1) {

        }
        false
    }

    override fun initPresenter(): PublicPresenter? {
        return PublicPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_public
    }

    override fun initView() {
        publicSwip.setColorSchemeColors(
            Color.rgb(33, 150, 243),
            Color.rgb(255, 193, 7),
            Color.rgb(0, 0, 0)
        )
        publicSwip.setOnRefreshListener(this)

        publicRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = PublicAdapter(R.layout.item_public_fragment)
        publicRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(this)

        //上拉加载更多
        loadMoreModule = adapter.loadMoreModule
        loadMoreModule.isAutoLoadMore = true
        loadMoreModule.setOnLoadMoreListener(listener = {
            presenter?.getPublic(page)
        })

        showLoading()
        presenter?.getPublic(page)
    }

    override fun lazyLoad() {
    }

    override fun onRefresh() {
        page = 1
        presenter?.getPublic(page)
    }

    override fun successData(data: List<Public>) {
            handle.postDelayed(Runnable {
                dismissLoading()
                adapter.addData(data)
                page++
            }, 2000)
    }

    override fun showLoading() {
        publicSwip.isRefreshing = true
    }

    override fun dismissLoading() {
        publicSwip.isRefreshing = false
        if(page > 1){
            loadMoreModule.loadMoreComplete()
        }
    }

    override fun errorMsg(msg: String?) {
        dismissLoading()
        ToastUtils.DEFAULT.show(msg)
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val item = adapter.getItem(position) as Public
        val intent = Intent()
        intent.setClass(activity, WebViewActivity::class.java)
        intent.putExtra("path", item.link)
        startActivity(intent)
    }
}