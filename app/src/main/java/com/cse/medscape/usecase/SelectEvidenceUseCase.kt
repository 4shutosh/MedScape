package com.cse.medscape.usecase

import android.content.Context
import com.cse.medscape.database.DiagnosisRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.cse.medscape.model.Evidence

class SelectEvidenceUseCase {

    suspend fun run(context: Context, evidenceId: String, evidenceChoiceId: String) {

        CoroutineScope(Dispatchers.IO).launch {

            val database = DiagnosisRoomDatabase.getDatabase(context)

            var isInitial = false

            val evidence = Evidence()

            evidence.setId(evidenceId)
            evidence.setChoiceId(evidenceChoiceId)

            if (database?.evidencesDao()?.getEvidenceIfPresent(evidenceId) != null) {

                evidence.setInitial(true)

                database.evidencesDao().updateEvidence(evidence)
            } else {

                evidence.setInitial(isInitial)

                database?.evidencesDao()?.insertEvidence(evidence)
            }

        }

    }

}