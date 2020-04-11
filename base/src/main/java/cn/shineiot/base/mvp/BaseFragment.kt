@file:Suppress("UNCHECKED_CAST")

package cn.shineiot.base.mvp

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.shineiot.base.utils.LogUtil

/**
 * @author Xuhao
 * created: 2017/10/25
 * desc:
 */

 abstract class BaseFragment<V : IBaseView, T : BasePresenter<V>>: Fragment(){

    var presenter : T? = null
    /**
     * 视图是否加载完毕
     */
    private var isViewPrepare = true
    /**
     * 数据是否加载过了
     */
    private var hasLoadData = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(),null)
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
//        LogUtil.e(isVisibleToUser)
        if (isVisibleToUser) {
//            lazyLoadDataIfPrepared()
            LogUtil.e("-------------------------------------")
            lazyLoad()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        LogUtil.e("onViewCreate")
        initP()
        initView()
//        isViewPrepare = true
//        lazyLoadDataIfPrepared()
    }

    /**
     * 初始化presenter
     */
    abstract fun initPresenter(): T?

    private fun initP() {
        presenter = initPresenter()
        if(null != presenter) {
            presenter!!.attachView(this as V)
        }
    }

    private fun lazyLoadDataIfPrepared() {
            lazyLoad()
        LogUtil.e(""+userVisibleHint+isViewPrepare+hasLoadData)
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            hasLoadData = true
        }
    }

    /*open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        lazyLoad()
    }*/


    /**
     * 加载布局
     */
    @LayoutRes
    abstract fun getLayoutId():Int

    /**
     * 初始化 ViewI
     */
    abstract fun initView()

    /**
     * 懒加载
     */
    abstract fun lazyLoad()

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }

}
