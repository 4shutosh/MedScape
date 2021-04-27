package com.cse.medscape.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cse.medscape.R
import com.cse.medscape.database.DiagnosisRoomDatabase
import com.cse.medscape.viewholder.RiskFactorViewHolder
import com.cse.medscape.viewmodel.RiskFactorsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.cse.medscape.model.RiskFactor

class RiskFactorsAdapter(
    val context: Context, val riskFactors: List<RiskFactor>, val layoutInflater: LayoutInflater,
    val riskFactorsViewModel: RiskFactorsViewModel
) : RecyclerView.Adapter<RiskFactorViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RiskFactorViewHolder {
        val view = layoutInflater.inflate(R.layout.risk_factor_layout, p0, false)
        return RiskFactorViewHolder(view)
    }

    override fun getItemCount(): Int {
        return riskFactors.size
    }

    override fun onBindViewHolder(p0: RiskFactorViewHolder, p1: Int) {

        // bind the view here

        p0.getRiskFactorName().text = riskFactors[p1].getName()
        p0.getRiskFactorGender().text = riskFactors[p1].getGender()

        p0.getRiskFactorToggleButton().setOnCheckedChangeListener { _, isChecked ->

            CoroutineScope(Dispatchers.IO).launch {

                val database = DiagnosisRoomDatabase.getDatabase(context)
                val riskFactor =
                    database?.riskFactorsDao()?.getRiskFactorWithId(riskFactors[p1].getId())

                riskFactor?.let {

                    var presentOrAbsent = "absent"

                    if (isChecked) {
                        presentOrAbsent = "present"
                    }

                    riskFactorsViewModel.onRiskFactorAdded(
                        context,
                        riskFactors[p1].getId(),
                        presentOrAbsent
                    )

                }
            }

        }

        val riskFactorIcon = p0.getRiskFactorSeverityIcon()
        if (riskFactors[p1].getSeverity() == "normal") {
            riskFactorIcon.setImageResource(R.drawable.normal)

        } else if (riskFactors[p1].getSeverity() == "serious") {
            riskFactorIcon.setImageResource(R.drawable.warning)
        } else if (riskFactors[p1].getSeverity() == "emergency") {
            riskFactorIcon.setImageResource(R.drawable.emergency)

        }

    }


}