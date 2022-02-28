package com.eliasjuniornino.zapzap.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eliasjuniornino.zapzap.databinding.FragmentHomeBinding
import com.eliasjuniornino.zapzap.ui.home.adapter.HomePagerAdapter
import com.eliasjuniornino.zapzap.ui.home.tabs.BaseTabListFragment
import com.eliasjuniornino.zapzap.ui.home.tabs.CallsListFragment
import com.eliasjuniornino.zapzap.ui.home.tabs.ChatListFragment
import com.eliasjuniornino.zapzap.ui.home.tabs.StatusListFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var fragmentsList: ArrayList<BaseTabListFragment>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        fragmentsList = arrayListOf(
            ChatListFragment.newInstance(),
            StatusListFragment.newInstance(),
            CallsListFragment.newInstance(),
        )

        binding.viewPagerHome.adapter = activity?.let { HomePagerAdapter(fragmentsList, it) }

        TabLayoutMediator(binding.tabLayoutHome, binding.viewPagerHome) { tabLayout, position ->
            tabLayout.text = getString(fragmentsList[position].getTabTitleRes())
        }.attach()

        return binding.root
    }

    companion object {
        /**
         * @return A new instance of fragment HomeFragment.
         */
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}