package com.carpenter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.carpenter.R
import com.carpenter.data.ChatViewModel
import com.carpenter.data.MessageItem
import com.carpenter.data.Repository
import com.carpenter.databinding.FragmentChatBinding
import com.carpenter.ui.adapter.MessageAdapter
import java.sql.Timestamp


const val user1 = "Sanyi"
const val user2 = "Feri"

class ChatFragment : Fragment(R.layout.fragment_chat) {

    private lateinit var chatViewModel: ChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentChatBinding.inflate(inflater, container, false)
        chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]

        binding.messagesListView.adapter = MessageAdapter()
        binding.messagesListView.layoutManager = LinearLayoutManager(this.context)
        val adapter: MessageAdapter = binding.messagesListView.adapter as MessageAdapter
        chatViewModel.getMessages().observe(viewLifecycleOwner, { messages ->
            (binding.messagesListView.adapter as MessageAdapter).setMessages(messages)
            adapter.notifyItemInserted(adapter.itemCount)
            binding.messagesListView.smoothScrollToPosition(adapter.itemCount - 1)
        })

        createListeners(binding)
        return binding.root
    }

    private fun createListeners(binding: FragmentChatBinding) {
        binding.buttonSendMessage.setOnClickListener(View.OnClickListener { sendMessage(binding) })
        binding.editTextMessage.setOnEditorActionListener { _, _, _ -> sendMessage((binding)) }
        binding.userswitch.setOnClickListener(View.OnClickListener {
            binding.userswitch.text = if (binding.userswitch.isChecked) user1 else user2
        })
    }

    /*
    * Return value determines if keyboard is staying open or closed
    */
    private fun sendMessage(binding: FragmentChatBinding): Boolean {
        val messageTextView = binding.editTextMessage
        val usersWitch = binding.userswitch

        if (messageTextView.text.isEmpty()) return true
        chatViewModel.addMessage(
            MessageItem(
                if (usersWitch.isChecked) user1 else user2,
                messageTextView.text.toString(),
                Timestamp(System.currentTimeMillis())
            )
        )
        messageTextView.text.clear()
        return true
    }
}