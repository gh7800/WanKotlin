package cn.shineiot.wankotlin.ui.fragments.home.public

import androidx.recyclerview.widget.LinearLayoutManager
import cn.shineiot.base.mvp.BaseFragment
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.Public
import kotlinx.android.synthetic.main.fragment_public.*

class PublicFragment :BaseFragment<PublicView,PublicPresenter>(),PublicView{
    private val page : Int = 1   //第x页
    private var adapter : PublicAdapter? = null
    var data = arrayListOf<Public>()

    override fun initPresenter(): PublicPresenter? {
        return PublicPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_public
    }

    override fun initView() {

        publicRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = PublicAdapter(R.layout.item_public_fragment,data)
        publicRecyclerView.adapter = adapter
//        adapter?.setEnableLoadMore(false)

        presenter?.getPublic(page)
    }

    override fun lazyLoad() {
    }

    override fun successData(data: List<Public>) {
        LogUtil.e(data)
        adapter?.setNewData(data)
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    override fun errorMsg(msg: String?) {

    }
}