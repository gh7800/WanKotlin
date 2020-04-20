package cn.shineiot.wankotlin.ui

import androidx.appcompat.widget.LinearLayoutCompat
import cn.shineiot.base.mvp.BaseActivity
import cn.shineiot.wankotlin.R
import com.just.agentweb.AgentWeb
import kotlinx.android.synthetic.main.activity_webview.*

class WebViewActivity : BaseActivity() {
    private lateinit var path :String

    override fun layoutId(): Int {
        return R.layout.activity_webview
    }

    override fun initView() {
        path = intent.extras.getString("path")
        setToolbar(toolbar,"文章详情")

        AgentWeb.with(this)
            .setAgentWebParent(linearLayout,LinearLayoutCompat.LayoutParams(-1,-1))
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
            .go(path)
    }

}