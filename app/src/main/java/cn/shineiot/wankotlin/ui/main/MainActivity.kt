package cn.shineiot.wankotlin.ui.main

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import cn.shineiot.base.mvp.BaseMVPActivity
import cn.shineiot.base.utils.LogUtil
import cn.shineiot.base.utils.ToastUtils
import cn.shineiot.wankotlin.R
import cn.shineiot.wankotlin.ui.fragments.blog.BlogFragment
import cn.shineiot.wankotlin.ui.fragments.home.HomeFragment
import cn.shineiot.wankotlin.ui.fragments.knowledge.KnowledgeFragment
import cn.shineiot.wankotlin.ui.fragments.navigation.NavigationFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 首页
 */
class MainActivity : BaseMVPActivity<MainView, MainPresenter>(), MainView {

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

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initView() {
        //消除图标是色块的问题，建议图标背景色为透明
        bottomNavigationView.itemIconTintList = null
        bottomNavigationView.setOnNavigationItemSelectedListener(listener)

        val homeFragment = HomeFragment()
        val blogFragment = BlogFragment()
        val knowledgeFragment = KnowledgeFragment()
        val navigationFragment = NavigationFragment()

        fragments.add(homeFragment)
        fragments.add(blogFragment)
        fragments.add(knowledgeFragment)
        fragments.add(navigationFragment)

        viewPager.addOnPageChangeListener(viewPagerChangeListener)
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
//        viewPager.offscreenPageLimit = 3
    }

    class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(p0: Int): Fragment {
            return fragments[p0]
        }

        override fun getCount(): Int {
            return fragments.size
        }


    }

    private val viewPagerChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(p0: Int) {
        }

        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
        }

        override fun onPageSelected(p0: Int) {
            bottomNavigationView.menu.getItem(p0).isChecked = true;
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
