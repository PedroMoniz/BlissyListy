package com.pedromoniz.blissylisty.data.sources.local.question

import com.pedromoniz.blissylisty.data.sources.QuestionSources


class QuestionLocalSource (
    private val questionDao: QuestionDao
) : QuestionSources {
    override suspend fun CheckServerAvailability(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}