package com.cse.medscape.model

import com.google.gson.annotations.SerializedName

enum class Severity(val string: String) {

    @SerializedName("normal")
    NORMAL("Normal"),

    @SerializedName("serious")
    SERIOUS("Serious"),

    @SerializedName("emergency")
    EMERGENCY("Emergency")

}