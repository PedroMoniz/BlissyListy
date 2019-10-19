package com.pedromoniz.blissylisty.di

import com.pedromoniz.blissylisty.data.repositories.QuestionRepository
import com.pedromoniz.blissylisty.data.sources.QuestionSources
import com.pedromoniz.blissylisty.data.sources.local.question.QuestionLocalSource
import com.pedromoniz.blissylisty.data.sources.remote.question.QuestionRemoteSource
import com.pedromoniz.blissylisty.domain.gateways.QuestionGateway
import org.koin.core.qualifier.named
import org.koin.dsl.module


val repositoriesModule = module {

    single<QuestionSources>(named("QuestionLocalSource"), createdAtStart = true) {
        QuestionLocalSource(get())
    }
    single<QuestionSources>(named("QuestionRemoteSource"), createdAtStart = true) {
        QuestionRemoteSource(get())
    }
    single<QuestionGateway>(createdAtStart = true) {
        QuestionRepository(
            get(named("QuestionLocalSource")),
            get(named("QuestionRemoteSource"))
        )
    }
}