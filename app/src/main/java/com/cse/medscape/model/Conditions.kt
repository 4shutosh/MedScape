package com.cse.medscape.model

import com.google.gson.annotations.SerializedName

class Conditions {

    @SerializedName("conditions")
    private var conditions : List<Condition> = emptyList()

    fun getConditions(): List<Condition> {

        return conditions
    }

}