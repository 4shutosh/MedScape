package com.cse.medscape.database.dao

import androidx.room.*
import com.cse.medscape.model.Evidence

@Dao
interface EvidencesDao {

    @Query("SELECT * from Evidences where evidenceId = :id")
    fun getEvidenceIfPresent(id : String) : Evidence

    @Query("SELECT * from Evidences")
    fun getAllEvidences() : List<Evidence>

    @Query("SELECT * from Evidences where choice_id = :present")
    fun getAllPresentEvidences(present : String) : List<Evidence>

    @Query("SELECT * from Evidences where choice_id = :absent")
    fun getAllAbsentEvidences(absent : String) : List<Evidence>

    @Update
    fun updateEvidence(evidence: Evidence)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvidence(evidence: Evidence)

    @Insert
    fun insertEvidences(evidences: List<Evidence>)
}