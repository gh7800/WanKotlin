package cn.shineiot.wankotlin.ui.fragments.home

import cn.shineiot.base.mvp.BaseFragment
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.base.utils.ToastUtils
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.Banner
import cn.shineiot.wankotlin.utils.GlideImageLoader
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * 首页fragment
 */
class HomeFragment : BaseFragment<HomeView, HomePresenter>(),HomeView {
    override fun initPresenter(): HomePresenter? {
        return HomePresenter()
    }

    override fun getLayoutId(): Int {
       return R.layout.fragment_home
    }

    override fun initView() {
        bannerView.setImageLoader(GlideImageLoader())
        bannerView.setIndicatorGravity(BannerConfig.RIGHT)

        presenter?.getBanner()
    }

    override fun lazyLoad() {
//        presenter?.getBanner()
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