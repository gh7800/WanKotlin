package cn.shineiot.wankotlin.ui.fragments.knowledge

import cn.shineiot.base.mvp.BaseFragment
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.ui.fragments.blog.BlogPresenter
import cn.shineiot.wankotlin.ui.fragments.blog.BlogView

class KnowledgeFragment : BaseFragment<KnowledgeView,KnowledgePresenter>(),KnowledgeView {
    override fun initPresenter(): KnowledgePresenter? {
        return KnowledgePresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_knowledge
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