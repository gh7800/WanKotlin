package cn.shineiot.wankotlin

import android.content.Context
import cn.shineiot.base.BaseApplication
import cn.shineiot.base.utils.SPutils
import kotlin.properties.Delegates
import kotlin.system.exitProcess

class App : BaseApplication() {
    companion object {
        lateinit var context: Context  //kotlin 委托模式， notNull 适用于那些无法在初始化阶段就确定属性值的场合

        fun logoutApp() {
            val sPutils = SPutils()
            sPutils.clear()

            exitProcess(0)
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}