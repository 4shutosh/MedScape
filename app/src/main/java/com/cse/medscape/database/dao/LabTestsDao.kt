package com.cse.medscape.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cse.medscape.model.LabTest

@Dao
interface LabTestsDao {

    @Query("SELECT * from LabTests")
    fun getAllLabTests(): MutableList<LabTest>

    @Query("SELECT * from LabTests where id = :id")
    fun getLabTestWithId(id: String): LabTest

    @Insert
    fun insert(labTests: List<LabTest>)

    @Query("DELETE FROM LabTests")
    fun deleteAll()

}