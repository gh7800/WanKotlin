package cn.shineiot.base.mvp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import cn.shineiot.base.utils.LogUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


/**
 * BaseActivity基类
 */
abstract class BaseMVPActivity<V : IBaseView, T : BasePresenter<V>> : AppCompatActivity(),CoroutineScope {
    lateinit var mContext: Context
    var presenter: T? = null

    //job用于控制协程,后面launch{}启动的协程,返回的job就是这个job对象
    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    @SuppressLint("RestrictedApi")
    fun setToolbar(toolbar: Toolbar, title: String,textView: AppCompatTextView) {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayUseLogoEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)

        textView.text = title
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())

        job = Job()
        LogUtil.e("coroutineContext---$coroutineContext")
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
        if (null != presenter) {
            presenter!!.attachView(this as V)
        }
    }

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

        job.cancel()
    }

}


