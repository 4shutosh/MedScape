package com.cse.medscape.retrofit

import com.google.gson.JsonObject
import kotlinx.coroutines.Deferred
import com.cse.medscape.model.*
import retrofit2.Response
import retrofit2.http.*

interface InfermedicaApiService {

    @GET("lab_tests")
    fun getAvailableLabTestsAsync() : Deferred<Response<List<LabTest>>>

    @GET("lab_tests/{labTestId}")
    fun getLabTestWithIdAsync(@Path("labTestId") labTestId : String) : Deferred<Response<LabTest>>

    @GET("symptoms")
    fun getSymptomsAsync() : Deferred<Response<List<Symptom>>>

    @GET("symptoms/s_{symptomId}")
    fun getSymptomWithIdAsync(@Path("symptomId") symptomId : String) : Deferred<Response<Symptom>>

    @GET("risk_factors")
    fun getRiskFactorsAsync() : Deferred<Response<List<RiskFactor>>>

    @POST("parse")
    fun getSymptomMentions(@Body userText: JsonObject) : Deferred<Response<MentionsList>>

    @POST("diagnosis")
    fun diagnose(@Body requestJSON : JsonObject) : Deferred<Response<Conditions>>

}