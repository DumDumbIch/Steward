package ru.dumdumbich.android.steward.domain.datasource


interface AppDataSource {
    fun loadScreenName(): String
    fun saveScreenName(name: String)
}
