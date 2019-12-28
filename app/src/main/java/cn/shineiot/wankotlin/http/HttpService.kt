package cn.shineiot.wankotlin.http

import cn.shineiot.base.mvp.BaseListResult
import cn.shineiot.base.mvp.BaseResult
import cn.shineiot.wankotlin.bean.Banner
import cn.shineiot.wankotlin.bean.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Observable

/**
 * API
 */
interface HttpService {

    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username")username: String,@Field("password")password: String) : Observable<BaseResult<User>>

    @GET("banner/json")
    fun getBanner():Observable<BaseListResult<Banner>>
}