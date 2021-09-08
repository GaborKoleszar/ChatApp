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

class MessageAdapter(val messages: MutableList<MessageItem>) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = MessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessageItem = messages[position]
        holder.sender.text = currentMessageItem.sender
        holder.message.text = currentMessageItem.message
        holder.timestamp.text = currentMessageItem.timestamp.toString()

        if (holder.sender.text.toString() == user1) {
            holder.root.gravity = Gravity.END
        } else {
            holder.root.gravity = Gravity.LEFT
        }

    }

    override fun getItemCount() = messages.size

    class MessageViewHolder(binding: MessageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val sender: TextView = binding.senderTextView
        val message: TextView = binding.messageTextView
        val timestamp: TextView = binding.timestampTextView
        val root: LinearLayout = binding.root
    }
}