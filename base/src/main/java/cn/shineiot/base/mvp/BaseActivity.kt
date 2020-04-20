package cn.shineiot.base.mvp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import cn.shineiot.base.R
import kotlinx.android.synthetic.main.include_toolbar.*


/**
 * BaseActivity基类
 */
abstract class BaseActivity : AppCompatActivity() {
    private var mContext: Context? = null

    @SuppressLint("RestrictedApi")
    fun setToolbar(toolbar: Toolbar, title: String) {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setDisplayUseLogoEnabled(true)
        actionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        actionBar?.setIcon(R.drawable.ic_launcher_round)
        toolbar_title.text = title
        actionBar?.setHomeButtonEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())

        mContext = this
        initView()
    }

    /**
     *  加载布局
     */
    abstract fun layoutId(): Int


    /**
     * 初始化数据
     */
//    abstract fun initData()

    /**
     * 初始化 View
     */
    abstract fun initView()


    /**
     * 打开软键盘
     */
    fun openKeyBord(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    /**
     * 关闭软键盘
     */
    fun closeKeyBord(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
    }


    override fun onDestroy() {
        super.onDestroy()
    }

}


