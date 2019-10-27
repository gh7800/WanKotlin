package cn.shineiot.base.utils

import android.util.Log

/**
 * log
 */
class LogUtil {

    companion object {
        private const val tag = "tag"

        fun e(msg: Any) {
            Log.e(tag, "$msg")
        }

        fun i(msg: Any){
            Log.i(tag,"$msg")
        }
    }
}