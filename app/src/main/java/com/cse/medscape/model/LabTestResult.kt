package com.cse.medscape.model

import com.google.gson.annotations.SerializedName

class LabTestResult {

    @SerializedName("id")
    private var id : String = ""

    @SerializedName("type")
    private var option : String = ""

    fun getOption() : String {
        return option
    }

    fun getId() : String {
        return id
    }

}