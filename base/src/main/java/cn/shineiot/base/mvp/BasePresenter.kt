package cn.shineiot.base.mvp

import androidx.lifecycle.Lifecycle
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by gf63
 */
open class BasePresenter<T : IBaseView> : IPresenter<T> {

    var mRootView: T? = null
    lateinit var mLifecycle : Lifecycle

    fun setLifeCycle(lifecycle: Lifecycle){
        this.mLifecycle = lifecycle
    }

    override fun attachView(mRootView: T) {
        this.mRootView = mRootView
    }

    override fun detachView() {
        mRootView = null
    }

    private val isViewAttached: Boolean
        get() = mRootView != null

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    private class MvpViewNotAttachedException internal constructor() :
        RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")

    fun <Y> addSubscription(observable: Observable<BaseResult<Y>>,abstractObserver: AbstractObserver<Y>){
            observable
                .compose(observableTransformer())
                .`as`(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(mLifecycle)))
                .subscribe(abstractObserver)
    }


    fun <T> observableTransformer(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}
