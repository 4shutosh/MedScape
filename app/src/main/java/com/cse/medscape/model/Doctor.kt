package com.cse.medscape.model

import com.google.firebase.database.PropertyName

class Doctor {

    @PropertyName("name")
    lateinit var name: String

    @PropertyName("speciality")
    lateinit var speciality: String

    @PropertyName("phone")
    lateinit var phone: String

}