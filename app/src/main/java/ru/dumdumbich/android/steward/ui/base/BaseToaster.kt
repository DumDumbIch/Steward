package ru.dumdumbich.android.steward.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import ru.dumdumbich.android.steward.tools.logger.Logger


abstract class BaseToaster<VB : ViewBinding>(
    context: Context,
    bindingInflater: (inflater: LayoutInflater) -> VB
) : KoinComponent {

    protected val logger: Logger by inject { parametersOf(this, true) }
    private val toastLayoutInflater: LayoutInflater = LayoutInflater.from(context)
    private var _ui: VB? = null
    protected val ui get() = _ui ?: error("Binding of ${this::class.java.simpleName} is null")

    protected val toast = Toast(context)

    init {
        _ui = bindingInflater.invoke(toastLayoutInflater)
        toast.view = ui.root
    }

    open fun show(message: String) {
        logger.toConsole(message)
    }
}
