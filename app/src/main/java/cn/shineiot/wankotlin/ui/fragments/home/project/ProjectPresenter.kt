package cn.shineiot.wankotlin.ui.fragments.home.project

import cn.shineiot.base.mvp.BasePresenter
import cn.shineiot.wankotlin.bean.PageEntity
import cn.shineiot.wankotlin.http.AbstractObserver
import cn.shineiot.wankotlin.http.HttpClient

class ProjectPresenter :BasePresenter<ProjectView>() {
    fun getProject(page:Int){
        HttpClient.getNewProject(page,object : AbstractObserver<PageEntity>(){
            override fun requestSuccess(t: PageEntity) {
                mRootView?.successData(t.datas)
            }

            override fun requestFaild(error: String?) {
                mRootView?.errorMsg(error)
            }

            override fun onCompleted() {

            }

        })
    }
}