package com.cse.medscape.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Mentions")
class Mention {

    @SerializedName("id")
    @PrimaryKey
    @NonNull
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

    @SerializedName("type")
    @NonNull
    @ColumnInfo(name = "type")
    private var type: MentionType = MentionType.SYMPTOM

    @SerializedName("orth")
    @Ignore
    private var orth: String = ""

    @SerializedName("choice_id")
    @NonNull
    @ColumnInfo(name = "choice_id")
    private var choiceId: String = ""

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

    fun setType(type: MentionType) {
        this.type = type
    }

    fun getType(): MentionType {
        return type
    }

    fun getOrth(): String {
        return orth
    }

    fun setChoiceId(choiceId: String) {
        this.choiceId = choiceId
    }

    fun getChoiceId(): String {
        return choiceId
    }

}