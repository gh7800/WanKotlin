package cn.shineiot.wankotlin.ui.fragments.blog

import cn.shineiot.base.utils.StringUtils
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.Public
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class BlogAdapter(layoutResId: Int) : BaseQuickAdapter<Public, BaseViewHolder>(layoutResId) ,LoadMoreModule{
    override fun convert(holder: BaseViewHolder, item: Public) {
        val title = StringUtils.delHtmlTags(item.title)
        holder.setText(R.id.title, title).setText(R.id.niceDate,item.niceDate)

        if (item.author.isNotEmpty())
            holder.setText(R.id.author, item.author)
        else {
            holder.setText(R.id.author, item.shareUser)
        }

        holder.setBackgroundResource(R.id.collectBt, R.drawable.icon_collect)
    }
}