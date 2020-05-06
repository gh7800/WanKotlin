package cn.shineiot.wankotlin.ui.fragments.blog

import cn.shineiot.base.mvp.BaseFragment
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.base.utils.ToastUtils
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.PageEntity
import kotlinx.android.synthetic.main.fragment_blog.*

class BlogFragment :BaseFragment<BlogView,BlogPresenter>() ,BlogView{
    private var page = 0
    private var adapter : BlogAdapter? = null

    override fun initPresenter(): BlogPresenter? {
        return BlogPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_blog
    }

    override fun initView() {
        adapter = BlogAdapter(R.layout.item_public_fragment)
        blogRecyclerView.adapter = adapter
    }

    override fun lazyLoad() {
        presenter?.getMyCollect(page)
    }

    override fun SuccessData(pageEntity: PageEntity) {
        LogUtil.e(pageEntity.total)
        adapter?.addData(pageEntity.datas)
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
    }

    override fun errorMsg(msg: String?) {
        ToastUtils.DEFAULT.show(msg)
    }
}