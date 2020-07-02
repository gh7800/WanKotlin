package cn.shineiot.wankotlin.ui

import android.content.Intent
import androidx.appcompat.widget.LinearLayoutCompat
import cn.shineiot.base.mvp.BaseActivity
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.ui.main.MainActivity
import com.just.agentweb.AgentWeb
import kotlinx.android.synthetic.main.activity_webview.*
import kotlinx.android.synthetic.main.include_toolbar.*

class WebViewActivity : BaseActivity() {
    private lateinit var path :String

    override fun layoutId(): Int {
        return R.layout.activity_webview
    }

    override fun initView() {
        path = intent.extras.getString("path")
        setToolbar(toolbar,"文章详情",toolbar_title)

        AgentWeb.with(this)
            .setAgentWebParent(linearLayout,LinearLayoutCompat.LayoutParams(-1,-1))
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
            .go(path)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        val intent = Intent(mContext,MainActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()

//        val intent = Intent(mContext,MainActivity::class.java)
//        startActivity(intent)
    }
}