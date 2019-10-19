package com.pedromoniz.blissylisty.data.sources.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pedromoniz.blissylisty.data.sources.local.models.QuestionLocalModel
import com.pedromoniz.blissylisty.data.sources.local.question.QuestionDao


@Database(entities = [QuestionLocalModel::class], version = 1)
abstract class QuestionDatabase : RoomDatabase() {

    abstract fun questionDao(): QuestionDao
}
