package com.cse.medscape.database.dao

import androidx.room.*
import com.cse.medscape.model.Mention

@Dao
interface MentionsDao {

    @Query("SELECT * from Mentions")
    fun getAllMentions(): List<Mention>

    @Query("SELECT * from Mentions where id = :id")
    fun getMentionWithId(id: String): Mention

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(mentions: List<Mention>)

    @Update
    fun update(mention: Mention)

    @Query("DELETE FROM Mentions")
    fun deleteAll()


}