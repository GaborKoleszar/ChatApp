package com.carpenter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.carpenter.R
import com.carpenter.data.MessageItem
import com.carpenter.data.Repository
import com.carpenter.databinding.FragmentChatBinding
import com.carpenter.ui.adapter.MessageAdapter
import java.sql.Timestamp

const val user1 = "Sanyi"
const val user2 = "Feri"

class ChatFragment : Fragment(R.layout.fragment_chat) {

    private val chatRepository: Repository by lazy { Repository() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        chatRepository.initDatabase()

        val binding = FragmentChatBinding.inflate(inflater, container, false)
        binding.messagesListView.adapter = MessageAdapter(chatRepository.createDummyList())
        binding.messagesListView.layoutManager = LinearLayoutManager(this.context)
        createListeners(binding)

        return binding.root
    }

    private fun createListeners(binding: FragmentChatBinding) {
        val recyclerView = binding.messagesListView
        binding.buttonSendMessage.setOnClickListener(View.OnClickListener {
            (recyclerView.adapter as MessageAdapter).messages.add(
                MessageItem(
                    if (binding.userswitch.isChecked) user1 else user2,
                    binding.editTextMessage.text.toString(),
                    Timestamp(System.currentTimeMillis())
                )
            )
            binding.editTextMessage.text.clear()
            (recyclerView.adapter as MessageAdapter).notifyDataSetChanged()
            recyclerView.scrollToPosition((recyclerView.adapter as MessageAdapter).itemCount - 1);

        })
        binding.editTextMessage.setOnEditorActionListener { _, _, _ ->
            binding.editTextMessage.text.clear()
            true
        }
        binding.userswitch.setOnClickListener(View.OnClickListener {
            binding.userswitch.text = if (binding.userswitch.isChecked) user1 else user2
        })
    }
}