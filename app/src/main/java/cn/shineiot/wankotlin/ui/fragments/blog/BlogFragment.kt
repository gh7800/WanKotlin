package cn.shineiot.wankotlin.ui.fragments.blog

import android.content.Intent
import android.view.View
import cn.shineiot.base.mvp.BaseFragment
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.base.utils.ToastUtils
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.PageEntity
import cn.shineiot.wankotlin.bean.Public
import cn.shineiot.wankotlin.ui.WebViewActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.chad.library.adapter.base.module.BaseLoadMoreModule
import kotlinx.android.synthetic.main.fragment_blog.*

class BlogFragment : BaseFragment<BlogView, BlogPresenter>(), BlogView, OnItemChildClickListener,
    OnItemClickListener, OnLoadMoreListener {
    private var page = 0
    private lateinit var adapter: BlogAdapter
    private var mPosition: Int = 0
    private lateinit var loadModule: BaseLoadMoreModule

    override fun initPresenter(): BlogPresenter? {
        return BlogPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_blog
    }

    override fun initView() {
        adapter = BlogAdapter(R.layout.item_public_fragment)
        blogRecyclerView.adapter = adapter

        adapter.setOnItemClickListener(this)
        adapter.setOnItemChildClickListener(this)
        adapter.addChildClickViewIds(R.id.collectBt)

        loadModule = adapter.loadMoreModule
        loadModule.setOnLoadMoreListener(this)
        loadModule.isAutoLoadMore = true
    }

    override fun lazyLoad() {
        page = 0
        presenter?.getMyCollect(page)
    }

    override fun SuccessData(pageEntity: PageEntity) {
        adapter.addData(pageEntity.datas)

        if (page == pageEntity.pageCount) {
            loadModule.loadMoreEnd(false) //没有数据了
        } else {
            page++
            loadModule.loadMoreComplete() //加载完成
        }

    }

    override fun SuccessUnCollect() {
        adapter.removeAt(mPosition)
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
    }

    override fun errorMsg(msg: String?) {
        ToastUtils.DEFAULT.show(msg)
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        if (view.id == R.id.collectBt) {
            mPosition = position
            val public = adapter.getItem(position) as Public
            presenter?.unCollect(public.id)
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val item = adapter.getItem(position) as Public
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra("path", item.link)
        startActivity(intent)
    }

    override fun onLoadMore() {
        presenter?.getMyCollect(page)
    }
}