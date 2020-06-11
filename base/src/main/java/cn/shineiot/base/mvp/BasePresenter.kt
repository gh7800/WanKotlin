package cn.shineiot.base.mvp

import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.GlobalScope

/**
 * Created by xuhao on 2017/11/16.
 */
open class BasePresenter<T : IBaseView> : IPresenter<T> {

    var globalScope = GlobalScope
    var mRootView: T? = null

    private var compositeDisposable = CompositeDisposable()


    override fun attachView(mRootView: T) {
        this.mRootView = mRootView
    }

    override fun detachView() {
        mRootView = null
        //LogUtil.e("detachView")
        //保证activity结束时取消所有正在执行的订阅
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }

    }

    private val isViewAttached: Boolean
        get() = mRootView != null

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    private class MvpViewNotAttachedException internal constructor() :
        RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")

    /*fun addSubscription(observable: Observable<*>,subscriber: Subscriber<BaseResult<T>>) {
        observable
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(subscriber)
    }*/
}