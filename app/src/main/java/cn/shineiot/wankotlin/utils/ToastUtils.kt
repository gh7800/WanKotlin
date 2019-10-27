package cn.shineiot.wankotlin.utils

import android.widget.Toast
import cn.shineiot.wankotlin.App

class ToastUtils {
    companion object {
        fun showToast(content: String?): Toast {
            val toast = Toast.makeText(App.context, content, Toast.LENGTH_SHORT)
            toast.show()
            return toast
        }
    }
}
