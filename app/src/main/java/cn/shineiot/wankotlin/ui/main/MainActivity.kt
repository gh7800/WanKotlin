package cn.shineiot.wankotlin.ui.main

import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import cn.shineiot.base.mvp.BaseActivity
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.base.utils.ToastUtils
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.ui.fragments.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 首页
 */
class MainActivity(fm: FragmentManager) : BaseActivity<MainView, MainPresenter>(), MainView {

    companion object {
        val fragments = ArrayList<Fragment>()
    }

    val navigationMenu = ArrayList<View>()

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initPresenter(): MainPresenter? {
        return MainPresenter()
    }

    override fun initView() {

        //消除图标是色块的问题，建议图标背景色为透明
        bottomNavigationView.itemIconTintList = null
        bottomNavigationView.setOnNavigationItemSelectedListener(listener)

        for (index in 1..4) {
            val fragment = HomeFragment()
            fragments.add(fragment)

//            navigationMenu.add(bottomNavigationView.menu.getItem(index).actionView)
        }

//        val fmg = supportFragmentManager
//        val pagerAdapter = ViewPagerAdapter()
        viewPager.addOnPageChangeListener(viewPagerChangeListener)
//        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.offscreenPageLimit = 2
        viewPager.currentItem = 0
    }

    class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private var fm: FragmentManager? = null

        init {
            this.fm = fm
        }

        override fun getItem(p0: Int): Fragment {
            return fragments[p0]
        }

        override fun getCount(): Int {
            return fragments.size
        }


    }

    private val viewPagerChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(p0: Int) {
            bottomNavigationView.menu.getItem(p0).isChecked = true;
        }

        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
        }

        override fun onPageSelected(p0: Int) {
        }

    }

    private val listener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.home -> {
                LogUtil.e("home")
                viewPager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.blog -> {
                LogUtil.e("blog")
                viewPager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.knowledge -> {
                LogUtil.e("knowledge")
                viewPager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation -> {
                LogUtil.e("navigation")
                viewPager.currentItem = 3
                return@OnNavigationItemSelectedListener true
            }
        }
        true
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
