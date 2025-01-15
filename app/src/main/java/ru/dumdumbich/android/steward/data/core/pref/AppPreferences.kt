package ru.dumdumbich.android.steward.data.core.pref

import android.content.Context
import ru.dumdumbich.android.steward.data.base.BasePreferences
import ru.dumdumbich.android.steward.domain.datasource.AppDataSource

/**
 * App preferences
 *
 * Этот источник данных создан для примера: интерфейс `AppDataSource` пока не несет смысловой нагрузки
 * В процессе разработки приложения содержание станет более осмысленным :-)
 *
 * @param context
 * @param dirName
 */
class AppPreferences(
    context: Context,
    dirName: String
) : BasePreferences(context, dirName), AppDataSource {

    companion object {
        private const val KEY_SAVED_APP_STATE = "ru_dumdumbich_steward_pref_app_state"
    }

    override fun loadScreenName(): String {
        val prefValue = sharedPrefs.getString(KEY_SAVED_APP_STATE, "")
        return prefValue ?: ""
    }

    override fun saveScreenName(name: String) {
        editor.putString(KEY_SAVED_APP_STATE, name)
    }
}
