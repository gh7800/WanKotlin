package cn.shineiot.wankotlin.bean

data class PageEntity (
    val curPage :Int ,
    val datas : ArrayList<Public>,
    val over : Boolean,
    val pageCount : Int,
    val size :Int,
    val total : Int
)