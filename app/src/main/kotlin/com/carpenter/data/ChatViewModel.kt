package com.carpenter.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ChatViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository = Repository()
    private val chatLiveData = repository.createDummyList()

    fun addMessage(messageToInsert: MessageItem) {
        repository.addMessage(messageToInsert)
    }

    fun getMessages(): LiveData<MutableList<MessageItem>> {
        return chatLiveData
    }

}