package com.cse.medscape.model

import com.google.firebase.database.PropertyName

class Remedy() {

    @PropertyName("name")
    lateinit var diseaseName: String

    @PropertyName("shortRemedy")
    lateinit var shortRemedy: String

    @PropertyName("link")
    lateinit var link: String

}