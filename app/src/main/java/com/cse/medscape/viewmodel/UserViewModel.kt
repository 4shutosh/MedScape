package com.cse.medscape.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class UserViewModel : ViewModel() {

    private var userName: String = ""

    private var userAge: Int = 0

    private var userGender: String = ""

    fun saveUserInfo(context: Context, name: String, age: Int, gender: String) {

        val sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)

        val sharedPreferencesEditor = sharedPreferences.edit()

        sharedPreferencesEditor.putString("userName", name).apply()
        sharedPreferencesEditor.putInt("userAge", age).apply()
        sharedPreferencesEditor.putString("userGender", gender).apply()

        // add database to firebase here
        val uid = FirebaseAuth.getInstance().currentUser.uid.toString()
        val database = FirebaseDatabase.getInstance().reference.child("Users").child(uid)
        database.child("name").setValue(name)
        database.child("userAge").setValue(age)
        database.child("gender").setValue(gender)

    }

    fun getUserName(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)

        return sharedPreferences.getString("userName", "")
    }

    fun getUserAge(context: Context): Int? {

        val sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)

        return sharedPreferences.getInt("userAge", 0)
    }

    fun getUserGender(context: Context): String? {

        val sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)

        return sharedPreferences.getString("userGender", "")
    }

}