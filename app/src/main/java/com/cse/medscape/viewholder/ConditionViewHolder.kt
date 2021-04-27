package com.cse.medscape.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cse.medscape.R

class ConditionViewHolder(item: View) : RecyclerView.ViewHolder(item) {

    private val conditionName = item.findViewById<TextView>(R.id.condition_txt_title)

    private val conditionProbability = item.findViewById<TextView>(R.id.condition_probability)

    fun getConditionName(): TextView {
        return conditionName
    }

    fun getConditionProbability(): TextView {
        return conditionProbability
    }

}