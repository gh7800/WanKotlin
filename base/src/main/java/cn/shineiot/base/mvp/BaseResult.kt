package cn.shineiot.base.mvp

data class BaseResult<T>(
    var data: T,
    var errorCode: Int?,
    var errorMsg: String
)