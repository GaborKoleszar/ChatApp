package com.carpenter.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.carpenter.data.MessageItem
import com.carpenter.data.Repository

class ChatViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository = Repository()
    private val chatLiveData = repository.createDummyList()

    fun addMessage(messageToInsert: MessageItem) {
        repository.addMessage(messageToInsert)
    }

    fun deleteMessage(messageToDelete: MessageItem) {
        repository.deleteMessage(messageToDelete)
    }

    fun getMessages(): LiveData<MutableList<MessageItem>> {
        return chatLiveData
    }

}