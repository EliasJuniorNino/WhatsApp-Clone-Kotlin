package com.eliasjuniornino.zapzap.ui.home.tabs

import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout

abstract class BaseTabListFragment : Fragment() {
    abstract fun tabLayoutBind(tab: TabLayout.Tab)
}