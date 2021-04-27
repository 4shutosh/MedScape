package com.cse.medscape.usecase

import android.content.Context
import android.widget.Toast
import com.cse.medscape.database.DiagnosisRoomDatabase
import com.cse.medscape.model.LabTest
import com.cse.medscape.retrofit.RetrofitClient

class FetchLabTestsUseCase(val context: Context) {

    suspend fun run(): List<LabTest> {

        println("Fetching Lab Tests here")

        var labTests = emptyList<LabTest>()

        val database = DiagnosisRoomDatabase.getDatabase(context)

        val sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)

        if (sharedPreferences.getBoolean("fetchedLabTests", false)) {

            // if data already fetched then get it from the com.cse.medscape.database
            println("Fetching Lab Tests from com.cse.medscape.database here")

            database?.let {

                return@run it.labTestsDao().getAllLabTests()
            }
        }

        // otherwise get data by making a call to web service api
        val labTestRequest = RetrofitClient.getApiService().getAvailableLabTestsAsync()

        val response = labTestRequest.await()

        println("Done awaiting for the response")

        if (response.isSuccessful) {
            println("Hey successfully got response")

            val labTestResponse = response.body()

            labTestResponse?.let {

                labTests = labTestResponse

            }

        } else {
            Toast.makeText(context, "Error fetching LabTests", Toast.LENGTH_LONG).show()
        }


        val editor = sharedPreferences.edit()
        editor.putBoolean("fetchedLabTests", true).apply()

        // store the information into the com.cse.medscape.database if fetched first time

        database?.labTestsDao()?.insert(labTests)

        return labTests

    }
}