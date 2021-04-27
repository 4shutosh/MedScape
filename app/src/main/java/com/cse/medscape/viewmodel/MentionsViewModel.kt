package com.cse.medscape.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cse.medscape.model.Mention
import com.cse.medscape.usecase.ParseSymptomsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MentionsViewModel : ViewModel() {

    var mentions: MutableLiveData<List<Mention>> = MutableLiveData()

    fun getSymptomMentions(context: Context, userMessage: String) {

        CoroutineScope(Dispatchers.IO).launch {

            val parseSymptomsUseCase = ParseSymptomsUseCase(context)

            mentions.postValue(parseSymptomsUseCase.run(userMessage))

        }

    }

}