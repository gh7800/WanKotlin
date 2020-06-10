package cn.shineiot.wankotlin.ui.main

import android.os.Build
import android.view.KeyEvent
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

    override fun initPresenter(): MainPresenter {
        return MainPresenter()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initView() {
        //消除图标是色块的问题，建议图标背景色为透明
        //bottomNavigationView.itemIconTintList = null
        bottomNavigationView.setOnNavigationItemSelectedListener(listener)

        viewPager.addOnPageChangeListener(viewPagerChangeListener)
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.offscreenPageLimit = 4
    }


    /**
     * ViewPagerAdapter
     * getItem里 fragment初始化
     * BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT  当fragment显示的时候执行onResume()
     */
    inner class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(p0: Int): Fragment {
            return when(p0){
                0 -> HomeFragment()
                1 -> BlogFragment()
                2 -> KnowledgeFragment()
                else -> NavigationFragment()
            }
        }

        override fun getCount(): Int {
            return 4
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
                viewPager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.blog -> {
                viewPager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.knowledge -> {
                viewPager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation -> {
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        super.onKeyDown(keyCode, event)

        return false
    }

}
