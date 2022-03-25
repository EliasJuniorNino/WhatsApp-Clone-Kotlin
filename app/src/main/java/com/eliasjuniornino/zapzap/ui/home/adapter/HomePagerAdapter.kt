package com.eliasjuniornino.zapzap.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.eliasjuniornino.zapzap.ui.home.tabs.BaseTabListFragment

class HomePagerAdapter(
    private val fragmentList: ArrayList<BaseTabListFragment>,
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = fragmentList.size
    override fun createFragment(position: Int): Fragment = fragmentList[position]
}