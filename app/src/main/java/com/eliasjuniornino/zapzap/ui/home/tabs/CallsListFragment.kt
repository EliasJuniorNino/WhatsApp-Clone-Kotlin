package com.eliasjuniornino.zapzap.ui.home.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eliasjuniornino.zapzap.R
import com.eliasjuniornino.zapzap.databinding.FragmentCallsListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [CallsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CallsListFragment : BaseTabListFragment() {
    override fun getTabTitleRes(): Int = R.string.calls

    lateinit var binding: FragmentCallsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCallsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        /**
         * @return A new instance of fragment ChatListFragment.
         */
        @JvmStatic
        fun newInstance() = CallsListFragment()
    }
}