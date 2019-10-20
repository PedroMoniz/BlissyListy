package com.pedromoniz.blissylisty.view.questionsListFeature.pagedListVersion

interface DataSourceDelegate<T> {
    fun requestPageData(startPosition: Int, loadSize: Int, onResult: (List<T>) -> Unit)
}