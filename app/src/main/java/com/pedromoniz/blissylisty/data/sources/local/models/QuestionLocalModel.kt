package com.pedromoniz.blissylisty.data.sources.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "questionsTable")
data class QuestionLocalModel @JvmOverloads constructor(
    @ColumnInfo(name = "title") var sessionToken: String = "",
    @PrimaryKey @ColumnInfo(name = "entryId") var id: String = UUID.randomUUID().toString()
)