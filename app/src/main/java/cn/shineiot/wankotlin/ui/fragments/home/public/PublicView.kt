package cn.shineiot.wankotlin.ui.fragments.home.public

import cn.shineiot.base.mvp.IBaseView
import cn.shineiot.wankotlin.bean.Public

interface PublicView :IBaseView{
    fun successData(data:List<Public>)
    fun SuccessCollect()
    fun SuccessUnCollect()
}