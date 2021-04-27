package com.cse.medscape.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "LabTests")
class LabTest {

    @SerializedName("id")
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private var id : String = ""

    @SerializedName("name")
    @ColumnInfo(name = "name")
    @NonNull
    private var name : String = ""

    @SerializedName("common_name")
    @ColumnInfo(name = "common_name")
    @NonNull
    private var commonName = ""

    @SerializedName("category")
    @ColumnInfo(name = "category")
    @NonNull
    private var category = ""

    @SerializedName("results")
    @ColumnInfo(name = "results")
    @NonNull
    private var optionsList : List<LabTestResult> = emptyList()

    fun setId(id : String) {
        this.id = id
    }

    fun getId() : String {
        return id
    }

    fun setName(name : String) {
        this.name = name
    }

    fun getName() : String {
        return name
    }

    fun setCommonName(commonName: String) {
        this.commonName = commonName
    }

    fun getCommonName() : String {
        return commonName
    }

    fun setCategory(category: String) {
        this.category = category
    }

    fun getCategory() : String {
        return category
    }

    fun setOptionsList(labTestResultOptions: List<LabTestResult>) {
        this.optionsList = labTestResultOptions
    }

    fun getOptionsList(): List<LabTestResult> {
        return optionsList
    }

}