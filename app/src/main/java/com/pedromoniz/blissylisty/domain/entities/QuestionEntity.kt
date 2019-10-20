package com.pedromoniz.blissylisty.domain.entities


data class QuestionEntity(
    val id: String,
    val question: String,
    val image_url: String,
    val thumb_url: String,
    val published_at: String,
    val choices: List<ChoiceEntity>
) {
    fun isValid() =
        id.isNotEmpty() ||
                question.isNotEmpty() ||
                image_url.isNotEmpty() ||
                thumb_url.isNotEmpty() ||
                published_at.isNotEmpty() ||
                choices.all { it.isValid() }
}

data class ChoiceEntity(
    var choice: String,
    val votes: String
) {
    fun isValid() = choice.isNotEmpty() || votes.isNotEmpty()
}