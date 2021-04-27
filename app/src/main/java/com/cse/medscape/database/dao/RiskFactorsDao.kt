package com.cse.medscape.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cse.medscape.model.RiskFactor

@Dao
interface RiskFactorsDao {

    @Query("SELECT * from RiskFactors")
    fun getAllRiskFactors(): List<RiskFactor>

    @Query("SELECT * from RiskFactors where id = :id")
    fun getRiskFactorWithId(id: String): RiskFactor

    @Insert
    fun insert(riskFactors: List<RiskFactor>)

    @Query("DELETE FROM RiskFactors")
    fun deleteAll()

}