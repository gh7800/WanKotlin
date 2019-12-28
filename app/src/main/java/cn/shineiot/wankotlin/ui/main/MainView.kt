package cn.shineiot.wankotlin.ui.main

import cn.shineiot.base.mvp.IBaseView
import cn.shineiot.wankotlin.bean.Banner

interface MainView : IBaseView{
    fun successBanner(banners: List<Banner>)
}