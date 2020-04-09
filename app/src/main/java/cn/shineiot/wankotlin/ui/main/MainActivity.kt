package cn.shineiot.wankotlin.ui.main

import cn.shineiot.base.mvp.BaseActivity
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.base.utils.ToastUtils
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.Banner
import cn.shineiot.wankotlin.utils.GlideImageLoader
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 首页
 */
class MainActivity : BaseActivity<MainView, MainPresenter>(), MainView {

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initPresenter(): MainPresenter? {
        return MainPresenter()
    }

    override fun initView() {
        bannerView.setImageLoader(GlideImageLoader())
        bannerView.setIndicatorGravity(BannerConfig.RIGHT)
        bottomNavigationView.itemIconTintList = null;
//        bottomNavigationView.animation = false;
        presenter?.getBanner()
    }

    override fun successBanner(banners: List<Banner>) {
        val list = ArrayList<String>()
        val listTitle = ArrayList<String>()
        for (banner in banners) {
            list.add(banner.imagePath)
            listTitle.add(banner.title)
        }
        bannerView.setImages(list)
        bannerView.setBannerTitles(listTitle)
        bannerView.start()
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun errorMsg(msg: String?) {
        LogUtil.e(msg)
        ToastUtils.DEFAULT.show(msg)
    }


}
