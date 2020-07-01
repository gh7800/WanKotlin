package cn.shineiot.wankotlin.ui.fragments.knowledge

import android.content.Intent
import android.view.View
import cn.shineiot.base.mvp.BaseMvpFragment
import cn.shineiot.base.utils.ToastUtils
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.PageEntity
import cn.shineiot.wankotlin.bean.Public
import cn.shineiot.wankotlin.ui.WebViewActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.chad.library.adapter.base.module.BaseLoadMoreModule
import kotlinx.android.synthetic.main.fragment_knowledge.*

class KnowledgeFragment : BaseMvpFragment<KnowledgeView, KnowledgePresenter>(),KnowledgeView ,OnItemClickListener,OnLoadMoreListener{
    private var page = 0
    private lateinit var adapter:KnowledgeAdapter
    private lateinit var loadMoreModule: BaseLoadMoreModule

    override fun initPresenter(): KnowledgePresenter? {
        return KnowledgePresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_knowledge
    }

    override fun initView() {
        adapter = KnowledgeAdapter(R.layout.item_wen_da)
        knowledgeRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(this)

        loadMoreModule = adapter.loadMoreModule
        loadMoreModule.setOnLoadMoreListener(this)
        loadMoreModule.isAutoLoadMore = true

    }

    override fun lazyLoad() {
        page  = 0
        presenter?.getWenDa(page)
    }

    override fun SuccessData(data: PageEntity) {
        adapter.addData(data.datas)

        if(page == data.pageCount){
            loadMoreModule.loadMoreEnd(false)
        }else{
            page++
            loadMoreModule.loadMoreComplete()
        }
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
    }

    override fun errorMsg(msg: String?) {
        ToastUtils.DEFAULT.show(msg)
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val item = adapter.getItem(position) as Public
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra("path", item.link)
        startActivity(intent)
    }

    override fun onLoadMore() {
        presenter?.getWenDa(page)
    }
}