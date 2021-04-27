package com.cse.medscape.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "RiskFactors")
class RiskFactor {

    @SerializedName("id")
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private var id: String = ""

    @SerializedName("name")
    @NonNull
    @ColumnInfo(name = "name")
    private var name: String = ""

    @SerializedName("seriousness")
    @NonNull
    @ColumnInfo(name = "severity")
    private var severity: String = ""

    @SerializedName("sex_filter")
    @NonNull
    @ColumnInfo(name = "sex_filter")
    private var gender: String = ""

    @SerializedName("common_name")
    @NonNull
    @ColumnInfo(name = "common_name")
    private var commonName: String = ""

    @SerializedName("category")
    @NonNull
    @ColumnInfo(name = "category")
    private var category: String = ""

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

    fun setCategory(category: String) {
        this.category = category
    }

    fun getCategory(): String {
        return category
    }

}