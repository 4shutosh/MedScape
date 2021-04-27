package com.cse.medscape.viewholder

import android.view.View
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.cse.medscape.R

class LabTestResultViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val radioButton = view.findViewById<RadioButton>(R.id.labtest_checkbox)

    fun getRadioButton(): RadioButton {

        return radioButton
    }

}