package cn.shineiot.base.mvp

/**
 * @author Jake.Ho
 * created: 2017/10/25
 * desc:
 */
interface IBaseView {

    fun showLoading()

    fun dismissLoading()

    fun errorMsg(msg: String?)

}
