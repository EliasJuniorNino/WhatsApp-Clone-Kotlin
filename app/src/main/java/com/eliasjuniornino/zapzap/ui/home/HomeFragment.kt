package com.eliasjuniornino.zapzap.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.eliasjuniornino.zapzap.databinding.FragmentHomeBinding
import com.eliasjuniornino.zapzap.ui.home.adapter.HomePagerAdapter
import com.eliasjuniornino.zapzap.ui.home.tabs.BaseTabListFragment
import com.eliasjuniornino.zapzap.ui.home.tabs.CallsListFragment
import com.eliasjuniornino.zapzap.ui.home.tabs.CameraFragment
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
            CameraFragment.newInstance(),
            ChatListFragment.newInstance(),
            StatusListFragment.newInstance(),
            CallsListFragment.newInstance(),
        )
        binding.viewPagerHome.adapter = activity?.let { HomePagerAdapter(fragmentsList, it) }
        binding.viewPagerHome.currentItem = 1
        val mediator = TabLayoutMediator(
            binding.tabLayoutHome,
            binding.viewPagerHome
        ) { tab, position ->
            fragmentsList[position].tabLayoutBind(tab)
        }
        mediator.attach()
        configureCameraIcon()
        return binding.root
    }

    private fun configureCameraIcon() {
        binding.tabLayoutHome.let { tabLayout ->
            val tabChild = tabLayout.getChildAt(0)
            if (tabChild is LinearLayout) {
                val layout = tabChild.getChildAt(0)
                if (layout is LinearLayout) {
                    val layoutParams = layout.layoutParams
                    if (layoutParams is LinearLayout.LayoutParams) {
                        layoutParams.weight = 0.4f
                        layout.layoutParams = layoutParams
                    }
                }
            }
        }
    }

    companion object {
        /**
         * @return A new instance of fragment HomeFragment.
         */
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}