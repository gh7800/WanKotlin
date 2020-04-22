package cn.shineiot.wankotlin.ui.fragments.home.public

import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.Public
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class PublicAdapter(layoutRes:Int) : BaseQuickAdapter<Public, BaseViewHolder>(layoutRes),LoadMoreModule{

    override fun convert(holder: BaseViewHolder, item: Public) {
        holder.setText(R.id.title,item.title)
        if (item.author.isNotEmpty())
            holder.setText(R.id.author, item.author)
        else {
            holder.setText(R.id.author, item.shareUser)
        }
    }
}