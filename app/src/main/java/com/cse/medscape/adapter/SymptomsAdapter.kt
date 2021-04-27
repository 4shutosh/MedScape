package com.cse.medscape.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cse.medscape.R
import com.cse.medscape.database.DiagnosisRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.cse.medscape.model.Symptom
import com.cse.medscape.viewholder.SymptomViewHolder
import com.cse.medscape.viewmodel.SymptomViewModel

class SymptomsAdapter(
    val context: Context, val symptoms: List<Symptom>,
    val layoutInflater: LayoutInflater, val symptomViewModel: SymptomViewModel
) : RecyclerView.Adapter<SymptomViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SymptomViewHolder {

        val view = layoutInflater.inflate(R.layout.symptom_layout, p0, false)

        return SymptomViewHolder(view)

    }

    override fun getItemCount(): Int {
        return symptoms.size
    }

    override fun onBindViewHolder(p0: SymptomViewHolder, p1: Int) {

        p0.getSymptomName().text = symptoms[p1].getName()
        p0.getSymptomGender().text = symptoms[p1].getGender()

        p0.getSymptomToggleButton().setOnCheckedChangeListener { _, isChecked ->

            CoroutineScope(Dispatchers.IO).launch {

                val database = DiagnosisRoomDatabase.getDatabase(context)
                val symptom = database?.symptomsDao()?.getSymptomWithId(symptoms[p1].getId())

                symptom?.let {

                    if (isChecked) {
                        it.setSymptomPresent("present")
                    } else {
                        it.setSymptomPresent("absent")
                    }

                    database.symptomsDao().updateSymptom(it)

                    symptomViewModel.onAddedSymptomEvidence(
                        context,
                        symptom.getId(),
                        symptom.getSymptomPresent()
                    )

                }

            }
        }

        val symptomIcon = p0.getSymptonSeverityIcon()
        if (symptoms[p1].getSeverity() == "normal") {
            symptomIcon.setImageResource(R.drawable.normal)

        } else if (symptoms[p1].getSeverity() == "serious") {
            symptomIcon.setImageResource(R.drawable.warning)
        } else if (symptoms[p1].getSeverity() == "emergency") {
            symptomIcon.setImageResource(R.drawable.emergency)

        }

    }


}