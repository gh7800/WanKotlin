package cn.shineiot.wankotlin.ui.fragments.home.project

import cn.shineiot.base.mvp.IBaseView
import cn.shineiot.wankotlin.bean.Project

interface ProjectView :IBaseView{
    fun successData(data:List<Project>)
}