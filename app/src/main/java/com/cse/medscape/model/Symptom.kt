package com.cse.medscape.model

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Symptoms")
class Symptom {

    @SerializedName("id")
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private var id: String = ""

    @SerializedName("name")
    @ColumnInfo(name = "name")
    @NonNull
    private var name: String = ""

    @SerializedName("seriousness")
    @ColumnInfo(name = "severity")
    @NonNull
    private var severity: String = ""

    @SerializedName("sex_filter")
    @ColumnInfo(name = "gender")
    @NonNull
    private var gender: String = ""

    @SerializedName("common_name")
    @ColumnInfo(name = "common_name")
    @NonNull
    private var commonName: String = ""

    @ColumnInfo(name = "present")
    @Nullable
    private var symptomPresent: String = "absent"

    fun setId(id: String) {
        this.id = id
    }

    fun getId(): String {
        return id
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getName(): String {
        return name
    }

    fun setSeverity(severity: String) {
        this.severity = severity
    }

    fun getSeverity(): String {
        return severity
    }

    fun setGender(gender: String) {
        this.gender = gender
    }

    fun getGender(): String {
        return gender
    }

    fun setCommonName(commonName: String) {
        this.commonName = commonName
    }

    fun getCommonName(): String {
        return commonName
    }

    fun setSymptomPresent(symptomPresentOrAbsent: String) {
        this.symptomPresent = symptomPresentOrAbsent
    }

    fun getSymptomPresent(): String {
        return symptomPresent
    }
}