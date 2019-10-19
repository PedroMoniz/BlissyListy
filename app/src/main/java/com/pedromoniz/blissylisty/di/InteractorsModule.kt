package com.pedromoniz.blissylisty.di

import com.pedromoniz.blissylisty.domain.useCases.*
import org.koin.dsl.module

val interactorModule = module {
    factory { AnswerAQuestionUseCase(get()) }
    factory { CheckServerHealthStatusUseCase(get()) }
    factory { FetchQuestionsUseCase(get()) }
    factory { GetQuestionUseCase(get()) }
    factory { ShareQuestionSearchQueryUseCase(get()) }
    factory { ShareQuestionUseCase(get()) }
}