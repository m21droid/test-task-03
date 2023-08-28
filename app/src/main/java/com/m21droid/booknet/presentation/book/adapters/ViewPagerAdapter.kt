package com.m21droid.booknet.presentation.book.adapters

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class ViewPagerAdapter(private val tabs: List<View>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any) = (view == `object`)

    override fun getCount() = tabs.size

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val layout = tabs[position]
        collection.addView(layout)
        return layout
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

}