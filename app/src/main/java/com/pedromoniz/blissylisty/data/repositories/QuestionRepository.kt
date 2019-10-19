package com.pedromoniz.blissylisty.data.repositories

import com.pedromoniz.blissylisty.data.sources.QuestionSources
import com.pedromoniz.blissylisty.domain.gateways.QuestionGateway


class QuestionRepository(
    private val questionLocalSources: QuestionSources,
    private val questionRemoteSources: QuestionSources
) : QuestionGateway {
}
