package com.cse.medscape.usecase

import android.content.Context
import android.widget.Toast
import com.cse.medscape.database.DiagnosisRoomDatabase
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.cse.medscape.model.Evidence
import com.cse.medscape.model.Mention
import com.cse.medscape.retrofit.RetrofitClient

class ParseSymptomsUseCase(val context: Context) {

    suspend fun run(userText: String): List<Mention> {

        println("Fetching Mentions here")

        var mentions = emptyList<Mention>()

        val database = DiagnosisRoomDatabase.getDatabase(context)

        // if data already fetched then get it from the com.cse.medscape.database
        println("Fetching Mentions from com.cse.medscape.database here")

        val userInformation = JsonObject()

        userInformation.addProperty("text", userText)

        // otherwise get data by making a call to web service api
        val mentionsDataRequest = RetrofitClient.getApiService().getSymptomMentions(userInformation)

        val response = mentionsDataRequest.await()

        println("Done awaiting for the response")

        if (response.isSuccessful) {
            println("Hey successfully got response")

            val mentionsResponse = response.body()

            mentionsResponse?.let {

                mentions = mentionsResponse.getMentionsList()

            }

        } else {

            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(context, "Error fetching SymptomMentions", Toast.LENGTH_LONG).show()
            }
        }

        // store the information into the com.cse.medscape.database if fetched first time

        // go through each and every mention and insert it accordingly into the mentions table and the
        // evidence table

        for (item in mentions) {

            val evidence = Evidence()
            evidence.setId(item.getId())
            evidence.setChoiceId(item.getChoiceId())
            evidence.setInitial(false)

            database?.let {

                it.evidencesDao().insertEvidence(evidence)

                it.mentionsDao().insert(mentions)

            }
        }

        return mentions
    }

}