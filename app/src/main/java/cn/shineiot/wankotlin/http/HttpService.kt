package cn.shineiot.wankotlin.http

import cn.shineiot.base.mvp.BaseListResult
import cn.shineiot.base.mvp.BaseResult
import cn.shineiot.wankotlin.bean.Banner
import cn.shineiot.wankotlin.bean.Project
import cn.shineiot.wankotlin.bean.Public
import cn.shineiot.wankotlin.bean.User
import retrofit2.http.*
import rx.Observable

/**
 * API
 */
interface HttpService {

    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username")username: String,@Field("password")password: String) : Observable<BaseResult<User>>

    @GET("banner/json")
//    suspend fun getBanner():BaseListResult<Banner>
    fun getBanner():Observable<BaseListResult<Banner>>

    @GET("article/list/{page}/json")
    fun getPublic(@Path("page")page:Int):Observable<BaseListResult<Public>>

    @GET("project/list/{page}/json")
    fun getProject(@Path("page")page:Int):Observable<BaseListResult<Project>>
}