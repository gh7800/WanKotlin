package cn.shineiot.wankotlin.ui.login

import cn.shineiot.base.mvp.IBaseView

interface LoginView {
   interface View: IBaseView{
        fun SuccessData(msg: String)
    }
}