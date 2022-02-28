package com.eliasjuniornino.zapzap.ui.home.tabs

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

abstract class BaseTabListFragment : Fragment() {
    @StringRes
    abstract fun getTabTitleRes(): Int
}