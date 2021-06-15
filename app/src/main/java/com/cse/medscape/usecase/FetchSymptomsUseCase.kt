package com.cse.medscape.usecase

import android.content.Context
import android.widget.Toast
import com.cse.medscape.database.DiagnosisRoomDatabase
import com.cse.medscape.model.Symptom
import com.cse.medscape.retrofit.RetrofitClient

class FetchSymptomsUseCase(val context: Context) {

    suspend fun run(): List<Symptom> {

        println("Calling Symptoms here")

        var symptomsRetrieved = emptyList<Symptom>()

        val database = DiagnosisRoomDatabase.getDatabase(context)


        val sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)

        if (sharedPreferences.getBoolean("fetchedSymptoms", false)) {

            // if data already fetched then get it from the com.cse.medscape.database
            println("Fetching symptoms from com.cse.medscape.database here")

            database?.let {

                return@run it.symptomsDao().getAllSymptoms()
            }
        }

        // otherwise get data by making a call to web service api
        val symptomsRequest = RetrofitClient.getApiService().getSymptomsAsync()

        val response = symptomsRequest.await()

        println("Done awaiting for the response")

        if (response.isSuccessful) {
            println("Hey successfully got response")

            val symptomsList = response.body()

            symptomsList?.let {

                symptomsRetrieved = symptomsList

            }

        } else {
            Toast.makeText(context, "Error fetching Symptoms", Toast.LENGTH_LONG).show()
        }

//
        val editor = sharedPreferences.edit()
        editor.putBoolean("fetchedSymptoms", true).apply()

        // store the information into the com.cse.medscape.database if fetched first time

        database?.symptomsDao()?.insert(symptomsRetrieved)

        return symptomsRetrieved
    }

}
