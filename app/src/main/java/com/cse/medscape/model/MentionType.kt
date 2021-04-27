package com.cse.medscape.model

import com.google.gson.annotations.SerializedName

enum class MentionType(val string: String) {

    @SerializedName("symptom")
    SYMPTOM("Symptom"),

    @SerializedName("risk_factor")
    RISK_FACTOR("Risk_Factor")

}