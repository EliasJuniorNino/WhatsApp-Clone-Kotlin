package com.eliasjuniornino.zapzap.ui.home.tabs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eliasjuniornino.zapzap.model.Chat
import com.eliasjuniornino.zapzap.repository.ChatsRepository

class ChatListFragmentViewModel : ViewModel() {
    val chats = MutableLiveData<List<Chat>>()

    fun getMessages() {
        chats.postValue(ChatsRepository.getChatsList())
    }
}