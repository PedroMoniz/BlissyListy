package com.pedromoniz.blissylisty.di

import androidx.room.Room
import com.pedromoniz.blissylisty.data.sources.local.database.QuestionDatabase
import org.koin.dsl.module


val databaseModule = module {
    single(createdAtStart = true) {
        Room.databaseBuilder(
            get(),
            QuestionDatabase::class.java,
            "question_database"
        ).build()
    }
    single(createdAtStart = true) { get<QuestionDatabase>().questionDao() }
}