package com.cse.medscape.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.cse.medscape.R
import com.example.mydiagnosis.viewholder.LabTestViewHolder
import com.cse.medscape.viewmodel.LabTestsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.cse.medscape.model.LabTest

class LabTestsAdapter(
    val context: Context, val labTests: MutableList<LabTest>, val layoutInflater: LayoutInflater,
    val labTestsViewModel: LabTestsViewModel
) : RecyclerView.Adapter<LabTestViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LabTestViewHolder {
        val view = layoutInflater.inflate(R.layout.labtest_layout, p0, false)

        return LabTestViewHolder(view)
    }

    override fun getItemCount(): Int {
        return labTests.size
    }

    override fun onBindViewHolder(p0: LabTestViewHolder, p1: Int) {

        p0.getLabTestName().text = labTests[p1].getName()
        p0.getLabTestCategory().text = labTests[p1].getCategory()

        val optionsList = labTests[p1].getOptionsList()
        println(labTests[p1].getOptionsList())

        for (item in 0 until optionsList.size) {

            val radioButton = RadioButton(context)
            radioButton.text = optionsList[item].getOption()
            radioButton.id = item

            p0.getRadioGroup().addView(radioButton)
        }

        p0.getRadioGroup().setOnCheckedChangeListener { _, checkedId ->

            CoroutineScope(Dispatchers.Main).launch {

                labTests.removeAt(p1)

                p0.getRadioGroup().removeAllViews()

                notifyDataSetChanged()

                labTestsViewModel.onLabTestResultAdded(context, optionsList[checkedId].getId())

            }

        }

    }

}