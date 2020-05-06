package cn.shineiot.wankotlin.ui.fragments.knowledge

import cn.shineiot.base.mvp.IBaseView
import cn.shineiot.wankotlin.bean.PageEntity

interface KnowledgeView :IBaseView {
    fun SuccessData(data:PageEntity)
}