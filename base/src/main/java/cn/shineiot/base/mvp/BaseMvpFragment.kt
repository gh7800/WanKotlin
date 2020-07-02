package cn.shineiot.base.mvp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import cn.shineiot.base.utils.LogUtil

abstract class BaseMvpFragment<V : IBaseView, T : BasePresenter<V>> : Fragment() {

    var presenter: T? = null

    /**
     * 视图是否加载完毕
     */
    private var isViewPrepare = true

    /**
     * 数据是否加载过了
     */
    private var hasLoadData = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), null)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        LogUtil.e("hidden0---$hidden+${this.javaClass.simpleName}")
        //lazyLoad()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initP()
        initView()
//        isViewPrepare = true
//        lazyLoadDataIfPrepared()
    }

    override fun onResume() {
        super.onResume()
        //lazyLoad()
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
        this.presenter = null
    }

    /**
     * 初始化presenter
     */
    abstract fun initPresenter(): T?

    private fun initP() {
        presenter = initPresenter()
        presenter?.attachView(this as V)
    }

    private fun lazyLoadDataIfPrepared() {
        lazyLoad()
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            hasLoadData = true
        }
    }


    /**
     * 加载布局
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * 初始化 ViewI
     */
    abstract fun initView()

    /**
     * 懒加载
     */
    abstract fun lazyLoad()


}
