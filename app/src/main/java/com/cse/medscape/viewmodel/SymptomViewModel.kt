package com.cse.medscape.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.cse.medscape.model.Symptom
import com.cse.medscape.usecase.FetchSymptomsUseCase
import com.cse.medscape.usecase.SelectEvidenceUseCase

class SymptomViewModel : ViewModel() {

    var symptomsFound: MutableLiveData<List<Symptom>> = MutableLiveData()

    fun fetchSymptoms(context: Context) {

        CoroutineScope(Dispatchers.IO).launch {

            val fetchSymptomsUseCaseResult = FetchSymptomsUseCase(context).run()

            symptomsFound.postValue(fetchSymptomsUseCaseResult)

        }

    }

    suspend fun onAddedSymptomEvidence(context: Context, symptomId: String, symptomChoice: String) {

        SelectEvidenceUseCase().run(context, symptomId, symptomChoice)

    }

}