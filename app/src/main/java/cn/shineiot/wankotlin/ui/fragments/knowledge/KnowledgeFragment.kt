package cn.shineiot.wankotlin.ui.fragments.knowledge

import cn.shineiot.base.mvp.BaseFragment
import cn.shineiot.base.utils.ToastUtils
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.PageEntity
import cn.shineiot.wankotlin.ui.fragments.blog.BlogPresenter
import cn.shineiot.wankotlin.ui.fragments.blog.BlogView
import kotlinx.android.synthetic.main.fragment_knowledge.*

class KnowledgeFragment : BaseFragment<KnowledgeView,KnowledgePresenter>(),KnowledgeView {
    private var page = 1
    private var adapter:KnowledgeAdapter? = null

    override fun initPresenter(): KnowledgePresenter? {
        return KnowledgePresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_knowledge
    }

    override fun initView() {
        adapter = KnowledgeAdapter(R.layout.item_public_fragment)
        knowledgeRecyclerView.adapter = adapter
    }

    override fun lazyLoad() {
        presenter?.getWenDa(page)
    }

    override fun SuccessData(data: PageEntity) {
        adapter?.addData(data.datas)
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
    }

    override fun errorMsg(msg: String?) {
        ToastUtils.DEFAULT.show(msg)
    }
}