package com.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {

    //	类型,shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)

    val newsTypeList = listOf("shehui", "guonei", "guoji", "yule", "keji")
    val titleList = listOf("社会", "国内", "国际", "娱乐", "科技")
    val fragmentList = ArrayList<NewsFragment>()

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        tabLayout = view.findViewById(R.id.news_tab_layout)
        viewPager = view.findViewById(R.id.news_view_pager)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        for (newsType in newsTypeList) {
            fragmentList.add(NewsFragment(newsType))
        }

        // 设置缓存数量
        viewPager.offscreenPageLimit = titleList.size

        viewPager.adapter = activity?.supportFragmentManager?.let { MyAdapter(it) }

        tabLayout.setupWithViewPager(viewPager)
    }

    inner class MyAdapter(fm: FragmentManager):
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return titleList[position]
        }

    }

}