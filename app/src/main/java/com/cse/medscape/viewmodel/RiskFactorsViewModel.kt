package com.cse.medscape.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.cse.medscape.model.RiskFactor
import com.cse.medscape.usecase.FetchRiskFactorsUseCase
import com.cse.medscape.usecase.SelectEvidenceUseCase

class RiskFactorsViewModel : ViewModel() {

    var riskFactors: MutableLiveData<List<RiskFactor>> = MutableLiveData()

    fun fetchRiskFactors(context: Context) {

        CoroutineScope(Dispatchers.IO).launch {

            val fetchRiskFactorsUseCase = FetchRiskFactorsUseCase(context)

            riskFactors.postValue(fetchRiskFactorsUseCase.run())

        }

    }

    suspend fun onRiskFactorAdded(
        context: Context,
        riskFactorId: String,
        riskFactorPresent: String
    ) {
        SelectEvidenceUseCase().run(context, riskFactorId, riskFactorPresent)

    }


}