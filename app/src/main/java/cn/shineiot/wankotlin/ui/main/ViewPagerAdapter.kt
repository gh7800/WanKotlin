package cn.shineiot.wankotlin.ui.main

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

class ViewPagerAdapter : PagerAdapter() {
    private var list = arrayOf<View>();

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
        container.removeView(list[position])
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        super.instantiateItem(container, position)
        container.addView(list[position])
        return list[position]
    }

}