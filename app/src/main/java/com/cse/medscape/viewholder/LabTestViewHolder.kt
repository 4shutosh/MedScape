package com.example.mydiagnosis.viewholder

import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cse.medscape.R

class LabTestViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val labTestName = view.findViewById<TextView>(R.id.LabTestName)

    private val labTestCategory = view.findViewById<TextView>(R.id.LabTestCategory)

    private val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)

    fun getLabTestName(): TextView {
        return labTestName
    }

    fun getLabTestCategory(): TextView {
        return labTestCategory
    }

    fun getRadioGroup(): RadioGroup {
        return radioGroup
    }

}
