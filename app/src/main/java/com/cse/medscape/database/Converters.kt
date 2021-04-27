package com.cse.medscape.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.cse.medscape.model.LabTestResult
import com.cse.medscape.model.MentionType

class Converters {

    companion object {

        @JvmStatic
        @TypeConverter
        fun fromMentionTypeToString(mentionType: MentionType): String {

            return mentionType.string

        }

        @JvmStatic
        @TypeConverter
        fun fromStringToMentionType(string: String): MentionType {

            return MentionType.valueOf(string)
        }

        @JvmStatic
        @TypeConverter
        fun fromLabTestResult(labTestResult: LabTestResult): String {

            return labTestResult.toString()
        }

        @JvmStatic
        @TypeConverter
        fun fromString(string: String): List<LabTestResult> {
            val listType = object : TypeToken<List<LabTestResult>>() {

            }.type
            return Gson().fromJson(string, listType)
        }

        @JvmStatic
        @TypeConverter
        fun fromOptionsList(labTestResults: List<LabTestResult>): String {

            return Gson().toJson(labTestResults)

        }

    }

}