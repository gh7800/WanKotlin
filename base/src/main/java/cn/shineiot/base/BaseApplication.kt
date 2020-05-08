package cn.shineiot.base

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

open class BaseApplication : Application() {
    companion object {
        lateinit var context: Context
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}