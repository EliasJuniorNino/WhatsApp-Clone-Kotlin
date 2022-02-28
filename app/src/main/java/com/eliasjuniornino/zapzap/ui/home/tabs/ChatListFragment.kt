package com.eliasjuniornino.zapzap.ui.home.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.eliasjuniornino.zapzap.R
import com.eliasjuniornino.zapzap.databinding.FragmentChatListBinding
import com.eliasjuniornino.zapzap.ui.home.adapter.ChatListAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [ChatListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChatListFragment : BaseTabListFragment() {
    override fun getTabTitleRes(): Int = R.string.chats

    lateinit var binding: FragmentChatListBinding
    private val viewModel: ChatListFragmentViewModel by activityViewModels()

    private lateinit var chatListAdapter: ChatListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatListBinding.inflate(inflater, container, false)

        chatListAdapter = ChatListAdapter(listOf())
        binding.recyclerView.adapter = chatListAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMessages()
        viewModel.chats.observe(viewLifecycleOwner) { chatListAdapter.setData(it) }
    }

    companion object {
        /**
         * @return A new instance of fragment ChatListFragment.
         */
        @JvmStatic
        fun newInstance() = ChatListFragment()
    }
}