package cn.shineiot.wankotlin.ui.fragments.home

import cn.shineiot.base.mvp.IBaseView
import cn.shineiot.wankotlin.bean.Banner

interface HomeView :IBaseView {
    fun successBanner(banners: List<Banner>)
}