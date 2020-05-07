package cn.shineiot.wankotlin.ui.fragments.knowledge

import cn.shineiot.base.mvp.BasePresenter
import cn.shineiot.wankotlin.bean.PageEntity
import cn.shineiot.wankotlin.http.AbstractObserver
import cn.shineiot.wankotlin.http.HttpClient

class KnowledgePresenter : BasePresenter<KnowledgeView>() {

    fun getWenDa(page:Int){
        HttpClient.getWenDa(page,object :AbstractObserver<PageEntity>(){
            override fun requestSuccess(t: PageEntity) {
                mRootView?.SuccessData(t)
            }

            override fun requestFaild(error: String?) {
                mRootView?.errorMsg(error)
            }

            override fun onCompleted() {

            }
        })

        /*RetrofitManager.service.getWenDa(page)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :Subscriber<BaseResult<PageEntity>>(){
                override fun onNext(result: BaseResult<PageEntity>?) {
                    if(result?.errorCode == 0){
                        mRootView?.SuccessData(result?.data)
                    }else{
                        mRootView?.errorMsg(result?.errorMsg)
                    }
                }

                override fun onCompleted() {

                }

                override fun onError(e: Throwable?) {
                    mRootView?.errorMsg(e?.message)
                }

            })*/
    }
}