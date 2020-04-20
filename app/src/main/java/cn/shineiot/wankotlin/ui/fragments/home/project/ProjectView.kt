package cn.shineiot.wankotlin.ui.fragments.home.project

import cn.shineiot.base.mvp.IBaseView
import cn.shineiot.wankotlin.bean.Project
import cn.shineiot.wankotlin.bean.Public

interface ProjectView :IBaseView{
    fun successData(data:List<Public>)
}