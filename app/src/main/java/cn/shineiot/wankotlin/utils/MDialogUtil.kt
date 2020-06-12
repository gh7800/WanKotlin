package cn.shineiot.wankotlin.utils

import android.content.Context
import com.maning.mndialoglibrary.MProgressDialog
import com.maning.mndialoglibrary.config.MDialogConfig
import com.maning.mndialoglibrary.listeners.OnDialogDismissListener

private val mBuild = MDialogConfig.Builder()

object MDialogUtil {

    /**
     * @param onDialogDismissListener dialog关闭监听
     */
    fun showLoading(
        context: Context,
        content: String,
        onDialogDismissListener: OnDialogDismissListener
    ) {
        mBuild.isCancelable(true)
            .isCanceledOnTouchOutside(false)
            .setOnDialogDismissListener(onDialogDismissListener)
        val mConfig = mBuild.build()
        MProgressDialog.showProgress(context, content, mConfig)
    }

    fun showLoading(context: Context, content: String) {
        mBuild.isCancelable(true).isCanceledOnTouchOutside(false)
        MProgressDialog.showProgress(context, content)
    }

    fun hideDialog() {
        MProgressDialog.dismissProgress()
    }

}