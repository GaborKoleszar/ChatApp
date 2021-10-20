package com.carpenter.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.sql.Timestamp

class Repository {

    fun initDatabase() {
        Firebase.database.setPersistenceEnabled(true)
    }

    private val mutableListOfMessages: MutableList<MessageItem> = mutableListOf()
    private val messagesListLiveData = MutableLiveData<MutableList<MessageItem>>()

    fun createDummyList(): LiveData<MutableList<MessageItem>> {
        mutableListOfMessages.add(MessageItem("Sanyi", "Szia!", Timestamp(System.currentTimeMillis())))
        mutableListOfMessages.add(MessageItem("Feri", "Szevasz!", Timestamp(System.currentTimeMillis())))
        mutableListOfMessages.add(MessageItem("Sanyi", "Hogy vagy?", Timestamp(System.currentTimeMillis())))
        mutableListOfMessages.add(MessageItem("Feri", "Jól, köszi! És te?", Timestamp(System.currentTimeMillis())))
        mutableListOfMessages.add(MessageItem("Sanyi", "Én is!", Timestamp(System.currentTimeMillis())))
        mutableListOfMessages.add(MessageItem("Feri", "Na jólvan akkor!", Timestamp(System.currentTimeMillis())))
        mutableListOfMessages.add(MessageItem("Sanyi", "Ja!", Timestamp(System.currentTimeMillis())))
        mutableListOfMessages.add(MessageItem("Feri", "Szevasz!", Timestamp(System.currentTimeMillis())))
        messagesListLiveData.apply { value = mutableListOfMessages }
        return messagesListLiveData
    }

    //TODO Update to background thread
    fun addMessage(messageItem: MessageItem)   {
        mutableListOfMessages.add(messageItem)
        messagesListLiveData.apply { value = mutableListOfMessages }
    }

    fun loadMessages(): MutableList<MessageItem> {
        //TODO return data from firebase
        return mutableListOf<MessageItem>()
    }

    //TODO Update to background thread
    fun deleteMessage(messageToDelete: MessageItem) {
        mutableListOfMessages.remove(messageToDelete)
        messagesListLiveData.apply { value = mutableListOfMessages }
    }

}