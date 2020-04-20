package cn.shineiot.wankotlin.ui.fragments.home.public

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import cn.shineiot.base.mvp.BaseFragment
import cn.shineiot.base.utils.ToastUtils
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.Public
import cn.shineiot.wankotlin.ui.WebViewActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.fragment_public.*

class PublicFragment :BaseFragment<PublicView,PublicPresenter>(),PublicView,OnItemClickListener{
    private val page : Int = 1   //第x页
    private lateinit var adapter : PublicAdapter

    override fun initPresenter(): PublicPresenter? {
        return PublicPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_public
    }

    override fun initView() {

        publicRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = PublicAdapter(R.layout.item_public_fragment)
        publicRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(this)

        presenter?.getPublic(page)
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

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val item  = adapter.getItem(position) as Public
        val intent = Intent()
        intent.setClass(activity,WebViewActivity::class.java)
        intent.putExtra("path",item.link)
        startActivity(intent)
    }
}