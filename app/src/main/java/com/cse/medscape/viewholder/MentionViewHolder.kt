package com.cse.medscape.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cse.medscape.R

class MentionViewHolder(item : View) : RecyclerView.ViewHolder(item) {

    private val typeIcon = item.findViewById<ImageView>(R.id.mention_type_icon)
    private val mentionName = item.findViewById<TextView>(R.id.mention_txt_title)
    private val mentionChoice = item.findViewById<TextView>(R.id.mention_txt_message)

    fun getTypeIcon() : ImageView {
        return typeIcon
    }

    fun getMentionName() : TextView {
        return mentionName
    }

    fun getMentionChoice() : TextView {
        return mentionChoice
    }

}