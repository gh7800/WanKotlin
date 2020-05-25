package cn.shineiot.wankotlin.http

import cn.shineiot.wankotlin.bean.Banner
import cn.shineiot.wankotlin.bean.PageEntity
import cn.shineiot.wankotlin.bean.Public
import cn.shineiot.wankotlin.bean.User
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object HttpClient {

     val service: HttpService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        RetrofitManager.getRetrofit().create(HttpService::class.java)
    }

    private fun <T> observableTransformer(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    //登录
    fun login(username: String, password: String, abstractObserver: AbstractObserver<User>) {
        service.login(username, password)
            .compose(observableTransformer())
            .subscribe(abstractObserver)
    }

    //退出登录
    fun logout(abstractObserver: AbstractObserver<User>){
        service.logout()
            .compose(observableTransformer())
            .subscribe(abstractObserver)
    }

    //获取收藏的文章
    fun getMyCollect(page: Int, abstractObserver: AbstractObserver<PageEntity>) {
        service.getMyCollect(page)
            .compose(observableTransformer())
            .subscribe(abstractObserver)
    }

    fun getWenDa(page: Int, abstractObserver: AbstractObserver<PageEntity>) {
        service.getWenDa(page)
            .compose(observableTransformer())
            .subscribe(abstractObserver)
    }

    //添加收藏
    fun collect(id: Int, abstractObserver: AbstractObserver<Public>) {
        service.collect(id)
            .compose(observableTransformer())
            .subscribe(abstractObserver)
    }

    //取消收藏
    fun unCollect(id: Int, abstractObserver: AbstractObserver<Public>) {
        service.uncollect(id)
            .compose(observableTransformer())
            .subscribe(abstractObserver)
    }

    //最新博文
    fun getNewPublic(page: Int, abstractObserver: AbstractObserver<PageEntity>) {
        service.getPublic(page)
            .compose(observableTransformer())
            .subscribe(abstractObserver)
    }

    //开源项目
    fun getNewProject(page: Int, abstractObserver: AbstractObserver<PageEntity>) {
        service.getProject(page)
            .compose(observableTransformer())
            .subscribe(abstractObserver)
    }

    //banner
    fun getBanner(abstractObserver: AbstractObserver<List<Banner>>) {
        service.getBanner()
            .compose(observableTransformer())
            .subscribe(abstractObserver)
    }
}