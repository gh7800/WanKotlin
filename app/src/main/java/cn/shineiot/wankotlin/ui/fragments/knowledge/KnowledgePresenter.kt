package cn.shineiot.wankotlin.ui.fragments.knowledge

import cn.shineiot.base.mvp.AbstractObserver
import cn.shineiot.base.mvp.BasePresenter
import cn.shineiot.wankotlin.bean.PageEntity
import cn.shineiot.wankotlin.http.HttpClient

class KnowledgePresenter : BasePresenter<KnowledgeView>() {

    fun getWenDa(page:Int){
        HttpClient.getWenDa(page,object : AbstractObserver<PageEntity>(){
            override fun requestSuccess(t: PageEntity) {
                mRootView?.SuccessData(t)
            }

            override fun requestFaild(error: String?) {
                mRootView?.errorMsg(error)
            }

        })

    }
}