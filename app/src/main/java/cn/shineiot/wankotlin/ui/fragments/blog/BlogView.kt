package cn.shineiot.wankotlin.ui.fragments.blog

import cn.shineiot.base.mvp.IBaseView
import cn.shineiot.wankotlin.bean.PageEntity

interface BlogView :IBaseView {
    fun SuccessData(pageEntity: PageEntity)
    fun SuccessUnCollect()
}