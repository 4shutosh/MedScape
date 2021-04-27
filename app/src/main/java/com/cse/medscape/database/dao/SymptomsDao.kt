package com.cse.medscape.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cse.medscape.model.Symptom

@Dao
interface SymptomsDao {

    @Query("SELECT * from Symptoms")
    fun getAllSymptoms(): List<Symptom>

    @Query("SELECT * from Symptoms where id = :id")
    fun getSymptomWithId(id: String): Symptom

    @Insert
    fun insert(symptoms: List<Symptom>)

    @Query("DELETE FROM Symptoms")
    fun deleteAll()

    @Update
    fun updateSymptom(symptom: Symptom)
}