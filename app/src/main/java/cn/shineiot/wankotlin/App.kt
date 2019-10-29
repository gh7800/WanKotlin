package cn.shineiot.wankotlin

import android.content.Context
import cn.shineiot.base.BaseApplication
import kotlin.properties.Delegates

class App : BaseApplication() {
    companion object {
        var context: Context by Delegates.notNull() //kotlin 委托模式， notNull 适用于那些无法在初始化阶段就确定属性值的场合
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}