package com.pedromoniz.blissylisty.view.loadingFeature

import androidx.lifecycle.ViewModel
import com.pedromoniz.blissylisty.Utils.BaseViewModel
import com.pedromoniz.blissylisty.domain.useCases.CheckServerHealthStatusUseCase

class LoadingViewModel(
    val checkServerHealthStatusUseCase: CheckServerHealthStatusUseCase
) : BaseViewModel() {
    // TODO: Implement the ViewModel
}
