package com.pedromoniz.blissylisty.Utils

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections


abstract class BaseViewModel : ViewModel() {

    val navDirection = HybridLiveEvent<NavDirections>()
    val failure = HybridLiveEvent<Failure>()

    private fun handleNavigation(navDirection: NavDirections) {
        this.navDirection.postValue(navDirection)
    }

    private fun handleFailure(failure: Failure) {
        this.failure.postValue(failure, false)
    }
}