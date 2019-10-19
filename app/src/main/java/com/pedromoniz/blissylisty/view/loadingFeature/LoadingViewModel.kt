package com.pedromoniz.blissylisty.view.loadingFeature

import androidx.lifecycle.viewModelScope
import com.pedromoniz.blissylisty.Utils.BaseViewModel
import com.pedromoniz.blissylisty.Utils.UseCase.NoParams
import com.pedromoniz.blissylisty.domain.useCases.CheckServerHealthStatusUseCase

class LoadingViewModel(
    val checkServerHealthStatusUseCase: CheckServerHealthStatusUseCase
) : BaseViewModel() {

    fun checkServerStatus() {
        checkServerHealthStatusUseCase(viewModelScope, NoParams()) {
            it.either(
                ::handleFailure,
                ::handleSuccess
            )
        }
    }

    private fun handleSuccess(success: Boolean) {
        handleNavigation(LoadingFragmentDirections.actionLoadingFragmentToQuestionsListFragment())
    }

}
