package com.cse.medscape.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cse.medscape.R
import com.cse.medscape.model.Mention
import com.cse.medscape.model.MentionType
import com.cse.medscape.viewholder.MentionViewHolder
import com.cse.medscape.viewmodel.MentionsViewModel

class MentionsAdapter(
    val context: Context, val mentions: MutableList<Mention>, val layoutInflater: LayoutInflater,
    val mentionsViewModel: MentionsViewModel
) : RecyclerView.Adapter<MentionViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MentionViewHolder {
        val view = layoutInflater.inflate(R.layout.mention_layout, p0, false)
        return MentionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mentions.size
    }

    override fun onBindViewHolder(p0: MentionViewHolder, p1: Int) {

        p0.getMentionName().text = mentions[p1].getName()

        p0.getMentionChoice().text = mentions[p1].getChoiceId()

        if (mentions[p1].getType() == MentionType.SYMPTOM) {
            p0.getTypeIcon().setImageResource(R.drawable.symptom_icon)
        } else {
            p0.getTypeIcon().setImageResource(R.drawable.risk_factor)
        }

    }

}