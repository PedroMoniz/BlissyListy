package com.pedromoniz.blissylisty.domain.useCases

import com.pedromoniz.blissylisty.Utils.Either
import com.pedromoniz.blissylisty.Utils.Failure
import com.pedromoniz.blissylisty.Utils.UseCase
import com.pedromoniz.blissylisty.domain.gateways.QuestionGateway


class CheckServerHealthStatusUseCase(private val gateway: QuestionGateway) :
    UseCase<Boolean, UseCase.NoParams>() {

    override suspend fun run(params: NoParams): Either<Failure, Boolean> {
        return try {
            val isAvailable = gateway.CheckQuestionsServerAvailability()
            return if (isAvailable) {
                Either.Right(isAvailable)
            } else {
                Either.Left(CheckServerHealthStatusFailure)
            }
        } catch (exp: Exception) {
            Either.Left(CheckServerHealthStatusFailure)
        }
    }

    object CheckServerHealthStatusFailure : Failure.FeatureFailure()
}