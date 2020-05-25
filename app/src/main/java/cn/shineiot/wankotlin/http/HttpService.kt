package cn.shineiot.wankotlin.http

import cn.shineiot.base.mvp.BaseResult
import cn.shineiot.wankotlin.bean.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

/**
 * API
 */
interface HttpService {

    //登录
    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username")username: String,@Field("password")password: String) : Observable<BaseResult<User>>

    @FormUrlEncoded
    @POST("user/login")
    fun loginBy(@Field("username")username: String,@Field("password")password: String) : Call<BaseResult<User>>

    //退出登录
    @GET("user/logout/json")
    fun logout():Observable<BaseResult<User>>

    @GET("banner/json")
    fun getBanner():Observable<BaseResult<List<Banner>>>

    //最新博文
    @GET("article/list/{page}/json")
    fun getPublic(@Path("page")page:Int):Observable<BaseResult<PageEntity>>

    //开源项目
    @GET("project/list/{page}/json")
    fun getProject(@Path("page")page:Int):Observable<BaseResult<PageEntity>>

    //我的收藏
    @GET("lg/collect/list/{page}/json")
    fun getMyCollect(@Path("page")page : Int):Observable<BaseResult<PageEntity>>

    //问答
    @GET("wenda/list/{page}/json ")
    fun getWenDa(@Path("page")page:Int):Observable<BaseResult<PageEntity>>

    //收藏站内文章
    @POST("lg/collect/{id}/json")
    fun collect(@Path("id")id:Int):Observable<BaseResult<Public>>

    //取消收藏站内文章
    @POST("lg/uncollect_originId/{id}/json")
    fun uncollect(@Path("id")id:Int):Observable<BaseResult<Public>>

}