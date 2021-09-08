package com.carpenter.data

import com.google.firebase.database.IgnoreExtraProperties
import java.sql.Timestamp

@IgnoreExtraProperties
data class MessageItem(val sender: String, val message: String, val timestamp: Timestamp)