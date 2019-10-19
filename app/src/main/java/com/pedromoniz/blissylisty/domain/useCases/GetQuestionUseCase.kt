package com.pedromoniz.blissylisty.domain.useCases

import com.pedromoniz.blissylisty.Utils.Either
import com.pedromoniz.blissylisty.Utils.Failure
import com.pedromoniz.blissylisty.Utils.UseCase
import com.pedromoniz.blissylisty.domain.gateways.QuestionGateway


class GetQuestionUseCase(private val gateway: QuestionGateway) :
    UseCase<Boolean, UseCase.NoParams>() {

    override suspend fun run(params: NoParams): Either<Failure, Boolean> {
//        return try {
//            val data = gateway.dummyCall()
//            return if (data.isValid()) {
//                Either.Right(data)
//            } else {
//                Either.Left(GetQuestionFailure)
//            }
//        }
//        catch (exp: Exception) {
//            Either.Left(Failure.NetworkConnection)
//        }
        return Either.Left(Failure.NetworkConnection)
    }

    object GetQuestionFailure : Failure.FeatureFailure()
}