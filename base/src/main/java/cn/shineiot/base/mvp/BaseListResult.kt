package cn.shineiot.base.mvp

data class BaseListResult<T> (
        var data: List<T> ,
        var errorCode: Int? ,
        var errorMsg: String
)