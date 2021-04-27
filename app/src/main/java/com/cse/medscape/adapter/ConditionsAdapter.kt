package com.example.mydiagnosis.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cse.medscape.R
import com.cse.medscape.viewmodel.DiagnosisResultViewModel
import com.cse.medscape.model.Condition
import com.cse.medscape.viewholder.ConditionViewHolder

class ConditionsAdapter(
    val context: Context, val conditions: List<Condition>, val layoutInflater: LayoutInflater,
    val diagnosisResultViewModel: DiagnosisResultViewModel
) : RecyclerView.Adapter<ConditionViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ConditionViewHolder {
        val view = layoutInflater.inflate(R.layout.diagnosis_layout, p0, false)
        return ConditionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return conditions.size
    }

    override fun onBindViewHolder(p0: ConditionViewHolder, p1: Int) {

        p0.getConditionName().text = conditions[p1].getName()

        p0.getConditionProbability().text = (conditions[p1].getProbability() * 100).toString() + "%"

    }


}