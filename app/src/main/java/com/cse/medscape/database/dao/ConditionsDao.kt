package com.cse.medscape.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cse.medscape.model.Condition

@Dao
interface ConditionsDao {

    @Insert
    fun insertConditions(conditions: List<Condition>)

    @Query("SELECT * from Conditions")
    fun getAllConditions() : List<Condition>

    @Query("DELETE FROM Conditions")
    fun deleteAll()

}