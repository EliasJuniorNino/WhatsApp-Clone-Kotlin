package com.eliasjuniornino.zapzap.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eliasjuniornino.zapzap.databinding.FragmentChatListItemBinding
import com.eliasjuniornino.zapzap.model.Chat

class ChatListAdapter(
    private var list: List<Chat>?
) : RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentChatListItemBinding.inflate(inflater,  parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list?.let { holder.bind(it[position]) }
    }

    override fun getItemCount(): Int = list?.size ?: 0

    fun setData(newList: List<Chat>?) {
        list = newList
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: FragmentChatListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chat: Chat) {
            binding.apply {
                name.text = chat.name
                date.text = chat.dateStr
                lastMessage.text = chat.lastMessage
                if (chat.image != null) {
                    image.setImageDrawable(chat.image)
                }
            }
        }
    }
}