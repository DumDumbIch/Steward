package ru.dumdumbich.android.steward.ui.base

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import ru.dumdumbich.android.steward.tools.logger.Logger


abstract class BaseScene : KoinComponent {

    protected val logger: Logger by inject<Logger> { parametersOf(this, true) }
}
