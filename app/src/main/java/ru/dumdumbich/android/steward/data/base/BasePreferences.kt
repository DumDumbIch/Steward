package ru.dumdumbich.android.steward.data.base

import android.content.Context
import android.content.SharedPreferences
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import ru.dumdumbich.android.steward.tools.logger.Logger


abstract class BasePreferences(context: Context, dirName: String) : KoinComponent {

    protected val logger: Logger by inject { parametersOf(this, true) }

    protected val sharedPrefs: SharedPreferences = context.getSharedPreferences(
        dirName,
        Context.MODE_PRIVATE
    )

    protected val editor: SharedPreferences.Editor by lazy {
        sharedPrefs.edit()
    }
}
