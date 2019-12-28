package cn.shineiot.base.mvp

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toolbar


/**
 * BaseActivity基类
 */
abstract class BaseActivity<V : IBaseView, T : BasePresenter<V>> : AppCompatActivity() {
    var mContext : Context? = null

    var presenter: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())

        mContext = this
        initP()
        initView()
    }

    /**
     *  加载布局
     */
    abstract fun layoutId(): Int

    /**
     * 初始化presenter
     */
    abstract fun initPresenter(): T?

    /**
     * 初始化数据
     */
//    abstract fun initData()

    /**
     * 初始化 View
     */
    abstract fun initView()

    @Suppress("UNCHECKED_CAST")
    fun initP() {
        presenter = initPresenter()
        if(null != presenter) {
            presenter!!.attachView(this as V)
        }
    }

//    fun setToolBar(toolbar: Toolbar,title: String){
//
//    }

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
        presenter?.detachView()
    }

}


