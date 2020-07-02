package cn.shineiot.wankotlin.ui.fragments.home

import androidx.fragment.app.Fragment
import cn.shineiot.base.mvp.BaseFragment
import cn.shineiot.base.mvp.BaseMvpFragment
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.base.utils.ToastUtils
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.bean.Banner
import cn.shineiot.wankotlin.ui.fragments.home.project.ProjectFragment
import cn.shineiot.wankotlin.ui.fragments.home.public.PublicFragment
import cn.shineiot.wankotlin.utils.GlideImageLoader
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * 首页fragment
 */
class HomeFragment : BaseMvpFragment<HomeView, HomePresenter>(), HomeView {
    private val tabTitle = arrayOf("最新博文", "最新项目")
    private val fragments = arrayListOf<Fragment>()
    private var currentFragment: Fragment ?= null

    override fun initPresenter(): HomePresenter? {
        return HomePresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        bannerView.setImageLoader(GlideImageLoader())
        bannerView.setIndicatorGravity(BannerConfig.RIGHT)

        for (index in tabTitle.indices) {
            home_tab_layout.addTab(home_tab_layout.newTab(), index )
            home_tab_layout.getTabAt(index)?.text = tabTitle[index]
        }

        home_tab_layout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                val index = p0?.position
                smartReplaceFragment(fragments[index!!])
            }
        })

        val publicFragment = PublicFragment()
        val projectFragment = ProjectFragment()
        fragments.add(publicFragment)
        fragments.add(projectFragment)

        smartReplaceFragment(publicFragment)

        presenter?.getBanner()
    }

    override fun lazyLoad() {

    }

    override fun successBanner(banners: List<Banner>) {
        val list = ArrayList<String>()
        val listTitle = ArrayList<String>()
        for (banner in banners) {
            list.add(banner.imagePath)
            listTitle.add(banner.title)
        }
        if(null != bannerView) {
            bannerView.setImages(list)
            bannerView.setBannerTitles(listTitle)
            bannerView.start()
        }
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun errorMsg(msg: String?) {
        ToastUtils.DEFAULT.show(msg)
    }

    fun smartReplaceFragment(fragment: Fragment) {
        val fragmentTransition = childFragmentManager.beginTransaction()
        if (!fragment.isAdded) {
            if(currentFragment !== null){
                fragmentTransition.hide(currentFragment!!).add(R.id.home_frameLayout, fragment).commit()
            }else {
                fragmentTransition.add(R.id.home_frameLayout, fragment).commit()
            }
        } else if(currentFragment !== fragment){
            fragmentTransition.hide(currentFragment!!).show(fragment).commit()
        }
        currentFragment = fragment
    }
}