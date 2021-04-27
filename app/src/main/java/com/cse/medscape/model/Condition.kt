package com.cse.medscape.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Conditions")
class Condition {

    @SerializedName("id")
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private var id: String = ""

    @SerializedName("name")
    @NonNull
    @ColumnInfo(name = "name")
    private var name: String = ""

    @SerializedName("common_name")
    @NonNull
    @ColumnInfo(name = "common_name")
    private var commonName: String = ""

    @SerializedName("probability")
    @NonNull
    @ColumnInfo(name = "probability")
    private var probability: Float = 0F

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

    fun setCommonName(commonName: String) {
        this.commonName = commonName
    }

    fun getCommonName(): String {
        return commonName
    }

    fun setProbability(probability: Float) {
        this.probability = probability
    }

    fun getProbability(): Float {
        return probability
    }

}