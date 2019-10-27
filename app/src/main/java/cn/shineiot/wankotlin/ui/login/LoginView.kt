package cn.shineiot.wankotlin.ui.login

import cn.shineiot.base.mvp.IBaseView
import cn.shineiot.wankotlin.bean.User

interface LoginView {
   interface View: IBaseView{
        fun successData(user: User)
    }
}