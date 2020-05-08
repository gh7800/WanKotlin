package cn.shineiot.wankotlin.ui

import android.os.Build
import androidx.annotation.RequiresApi
import cn.shineiot.base.mvp.BaseActivity
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.utils.Constants
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_photo.*

class PhotoActivity : BaseActivity(){
    override fun layoutId(): Int {
        return R.layout.activity_photo
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initView() {

        val bundle = intent.extras
        val url = bundle?.get(Constants.PATH)

        Glide.with(this).load(url).into(dragPhotoView)
        dragPhotoView.setOnExitListener { p0, p1, p2, p3, p4 -> finish() }
    }
}