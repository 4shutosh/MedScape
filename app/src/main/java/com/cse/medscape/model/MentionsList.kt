package com.cse.medscape.model

import com.google.gson.annotations.SerializedName

class MentionsList {

    @SerializedName("mentions")
    private var mentionsList: List<Mention> = emptyList()

    fun getMentionsList(): List<Mention> {
        return mentionsList
    }

}