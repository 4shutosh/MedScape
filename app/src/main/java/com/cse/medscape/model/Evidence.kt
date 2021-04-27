package com.cse.medscape.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Evidences")
class Evidence() {

    @ColumnInfo(name = "evidenceId")
    @PrimaryKey
    @NonNull
    private var id: String = ""

    @ColumnInfo(name = "choice_id")
    @NonNull
    private var choiceId: String = ""

    @ColumnInfo(name = "initial")
    @NonNull
    private var initial: Boolean = false

    fun setId(id: String) {
        this.id = id
    }

    fun getId(): String {
        return id
    }

    fun setChoiceId(choiceId: String) {
        this.choiceId = choiceId
    }

    fun getChoiceId(): String {
        return choiceId
    }

    fun setInitial(initial: Boolean) {
        this.initial = initial
    }

    fun getInitial(): Boolean {
        return initial
    }

}