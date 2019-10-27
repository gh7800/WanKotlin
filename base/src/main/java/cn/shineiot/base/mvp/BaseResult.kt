package cn.shineiot.base.mvp

data class BaseResult<T> (
        var data: T ,
        var error_Code: Int? ,
        var error_Msg: String
)