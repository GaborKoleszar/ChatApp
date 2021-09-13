package com.carpenter.ui.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.carpenter.data.MessageItem
import com.carpenter.databinding.MessageItemBinding
import com.carpenter.ui.user1

class MessageAdapter :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    private var mMessages: MutableList<MessageItem> = mutableListOf<MessageItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = MessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessageItem = mMessages[position]
        holder.sender.text = currentMessageItem.sender
        holder.message.text = currentMessageItem.message
        holder.timestamp.text = currentMessageItem.timestamp.toString()

        if (holder.sender.text.toString() == user1) {
            holder.root.gravity = Gravity.END
        } else {
            holder.root.gravity = Gravity.LEFT
        }

    }

    fun setMessages(messages: MutableList<MessageItem>) {
        mMessages = messages
    }

    override fun getItemCount() = mMessages.size

    class MessageViewHolder(binding: MessageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val sender: TextView = binding.senderTextView
        val message: TextView = binding.messageTextView
        val timestamp: TextView = binding.timestampTextView
        val root: LinearLayout = binding.root
    }
}