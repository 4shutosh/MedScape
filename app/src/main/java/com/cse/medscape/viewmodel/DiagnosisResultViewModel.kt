package com.cse.medscape.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.cse.medscape.model.Condition
import com.cse.medscape.usecase.DiagnoseUseCase

class DiagnosisResultViewModel : ViewModel() {

    var conditions: MutableLiveData<List<Condition>> = MutableLiveData()

    fun getDiagnosis(context: Context, userAge: Int?, userGender: String?) {

        CoroutineScope(Dispatchers.IO).launch {

            val useCase = DiagnoseUseCase(context, userAge, userGender)

            conditions.postValue(useCase.run())

        }

    }

}