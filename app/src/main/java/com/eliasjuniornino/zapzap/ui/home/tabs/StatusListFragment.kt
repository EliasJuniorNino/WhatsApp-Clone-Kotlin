package com.eliasjuniornino.zapzap.ui.home.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eliasjuniornino.zapzap.R
import com.eliasjuniornino.zapzap.databinding.FragmentStatusListBinding
import com.google.android.material.tabs.TabLayout

/**
 * A simple [Fragment] subclass.
 * Use the [StatusListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatusListFragment : BaseTabListFragment() {
    override fun tabLayoutBind(tab: TabLayout.Tab) {
        tab.setText(R.string.status)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentStatusListBinding.inflate(inflater, container, false).root
    }

    companion object {
        /**
         * @return A new instance of fragment ChatListFragment.
         */
        @JvmStatic
        fun newInstance() = StatusListFragment()
    }
}