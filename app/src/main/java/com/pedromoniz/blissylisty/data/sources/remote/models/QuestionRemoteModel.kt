package com.pedromoniz.blissylisty.data.sources.remote.models

import com.pedromoniz.blissylisty.domain.entities.ChoiceEntity
import com.pedromoniz.blissylisty.domain.entities.QuestionEntity
import com.squareup.moshi.Json


data class QuestionRemoteModel(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "question") val question: String,
    @field:Json(name = "image_url") val image_url: String,
    @field:Json(name = "thumb_url") val thumb_url: String,
    @field:Json(name = "published_at") val published_at: String,
    @field:Json(name = "choices") val choices: List<ChoiceRemoteModel>
) {
    fun toQuestion() = QuestionEntity(id, question, image_url, thumb_url, published_at, choices.map { it.toChoice() } )

    companion object {
        fun empty() = QuestionRemoteModel("", "", "", "", "", emptyList())
    }
}

data class ChoiceRemoteModel(
    @field:Json(name = "choice") val choice: String,
    @field:Json(name = "votes") val votes: String
) {
    fun toChoice() = ChoiceEntity(choice, votes)

    companion object {
        fun empty() = ChoiceRemoteModel("", "")
    }
}
