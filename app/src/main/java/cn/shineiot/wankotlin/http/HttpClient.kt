package cn.shineiot.wankotlin.http

import cn.shineiot.wankotlin.bean.Banner
import cn.shineiot.wankotlin.bean.PageEntity
import cn.shineiot.wankotlin.bean.Public
import cn.shineiot.wankotlin.bean.User
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

object HttpClient {

    private val service: HttpService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        RetrofitManager.getRetrofit().create(HttpService::class.java)
    }

    fun login(username: String, password: String, abstractObserver: AbstractObserver<User>) {
        service.login(username, password)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(abstractObserver)
    }

    //获取收藏的文章
    fun getMyCollect(page: Int,abstractObserver: AbstractObserver<PageEntity>) {
        service.getMyCollect(page)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(abstractObserver)
    }

    fun getWenDa(page :Int,abstractObserver: AbstractObserver<PageEntity>){
        service.getWenDa(page)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(abstractObserver)
    }

    //添加收藏
    fun collect(id : Int,abstractObserver: AbstractObserver<Public>){
        service.collect(id)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(abstractObserver)
    }

    //取消收藏
    fun unCollect(id : Int,abstractObserver: AbstractObserver<Public>){
        service.uncollect(id)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(abstractObserver)
    }

    //最新博文
    fun getNewPublic(page:Int,abstractObserver: AbstractObserver<PageEntity>){
        service.getPublic(page)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(abstractObserver)
    }

    //开源项目
    fun getNewProject(page:Int,abstractObserver: AbstractObserver<PageEntity>){
        service.getProject(page)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(abstractObserver)
    }

    //banner
    fun getBanner(abstractObserver: AbstractObserver<List<Banner>>){
        service.getBanner()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(abstractObserver)
    }
}