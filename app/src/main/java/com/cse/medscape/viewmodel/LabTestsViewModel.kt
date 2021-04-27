package com.cse.medscape.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cse.medscape.usecase.FetchLabTestsUseCase
import com.cse.medscape.usecase.SelectEvidenceUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.cse.medscape.model.LabTest

class LabTestsViewModel : ViewModel() {

    var labTests: MutableLiveData<List<LabTest>> = MutableLiveData()

    fun fetchLabTests(context: Context) {

        CoroutineScope(Dispatchers.IO).launch {

            val useCase = FetchLabTestsUseCase(context)

            labTests.postValue(useCase.run())

        }

    }

    suspend fun onLabTestResultAdded(context: Context, labTestResultId: String) {

        SelectEvidenceUseCase().run(context, labTestResultId, "present")

    }


}