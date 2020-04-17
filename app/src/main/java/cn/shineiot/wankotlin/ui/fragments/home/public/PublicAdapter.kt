package cn.shineiot.wankotlin.ui.fragments.home.public

import cn.shineiot.base.utils.LogUtil
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.Public
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class PublicAdapter(layouId:Int,data:List<Public>) : BaseQuickAdapter<Public, BaseViewHolder>(layouId,data){

    override fun convert(holder: BaseViewHolder, item: Public) {
        LogUtil.e(item.author)
        holder.setText(R.id.title,item.title)
        holder.setText(R.id.author,item.author)
    }
}