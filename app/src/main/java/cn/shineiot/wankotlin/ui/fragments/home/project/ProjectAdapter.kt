package cn.shineiot.wankotlin.ui.fragments.home.project

import android.widget.ImageView
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.Public
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class ProjectAdapter(layoutRes:Int) :BaseQuickAdapter<Public, BaseViewHolder>(layoutRes) {

    override fun convert(helper: BaseViewHolder, item: Public) {
        helper.setText(R.id.project_textView,item.title)
        if(item.author.isNotEmpty()) {
            helper.setText(R.id.project_author, item.author)
        }else{
            helper.setText(R.id.project_author,item.shareUser)
        }

        val img = helper.getView<ImageView>(R.id.project_image)
        Glide.with(context).load(item.envelopePic).into(img)
    }
}