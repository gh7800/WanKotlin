package cn.shineiot.wankotlin

import android.content.Context
import cn.shineiot.base.BaseApplication
import cn.shineiot.base.utils.SharePreutils
import kotlin.system.exitProcess

class App : BaseApplication() {
    companion object {
        lateinit var context: Context

        val sPutils = SharePreutils()
        fun logoutApp() {
            sPutils.clear()

            exitProcess(0)
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}
