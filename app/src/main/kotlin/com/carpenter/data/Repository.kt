package com.carpenter.data

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.sql.Timestamp

class Repository {

    fun initDatabase() {
        Firebase.database.setPersistenceEnabled(true)
    }

    private val listOfMessages = mutableListOf<MessageItem>()

    fun createDummyList(): MutableList<MessageItem> {
        listOfMessages.add(MessageItem("Sanyi", "Szia!", Timestamp(System.currentTimeMillis())))
        listOfMessages.add(MessageItem("Feri", "Szevasz!", Timestamp(System.currentTimeMillis())))
        listOfMessages.add(MessageItem("Sanyi", "Hogy vagy?", Timestamp(System.currentTimeMillis())))
        listOfMessages.add(MessageItem("Feri", "Jól, köszi! És te?", Timestamp(System.currentTimeMillis())))
        listOfMessages.add(MessageItem("Sanyi", "Én is!", Timestamp(System.currentTimeMillis())))
        listOfMessages.add(MessageItem("Feri", "Na jólvan akkor!", Timestamp(System.currentTimeMillis())))
        listOfMessages.add(MessageItem("Sanyi", "Ja!", Timestamp(System.currentTimeMillis())))
        listOfMessages.add(MessageItem("Feri", "Szevasz!", Timestamp(System.currentTimeMillis())))
        return listOfMessages
    }

    fun loadMessages(): MutableList<MessageItem> {
        //TODO return data from firebase
        return mutableListOf<MessageItem>()
    }

}