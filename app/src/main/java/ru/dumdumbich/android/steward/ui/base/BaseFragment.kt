package ru.dumdumbich.android.steward.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import ru.dumdumbich.android.steward.tools.logger.Logger
import ru.dumdumbich.android.steward.ui.core.snack.SimpleSnack
import ru.dumdumbich.android.steward.ui.core.toast.CenterToaster
import ru.dumdumbich.android.steward.ui.core.toast.SimpleToaster


abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment(), KoinComponent {

    // вместо isDebug = true/false передавать значение BuildConfig.Debug - уточнить как это правильно сделать
    protected val logger: Logger by inject { parametersOf(this, true) }
    protected val simpleToaster: SimpleToaster by inject()
    protected val centerToaster: CenterToaster by inject()
    protected lateinit var simpleSnack: SimpleSnack

    private var _ui: VB? = null
    protected val ui get() = _ui ?: error("Binding of ${this::class.java.simpleName} is null")

    override fun onAttach(context: Context) {
        logger.toConsole("onAttach() called with: context = $context")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        logger.toConsole("onCreate() called with: savedInstanceState = $savedInstanceState")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logger.toConsole(
            "onCreateView() called with: inflater = $inflater, " +
                    "container = $container, " +
                    "savedInstanceState = $savedInstanceState"
        )
        _ui = bindingInflater.invoke(layoutInflater)
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logger.toConsole(
            "onViewCreated() called with: view = $view, " +
                    "savedInstanceState = $savedInstanceState"
        )
        super.onViewCreated(view, savedInstanceState)
        simpleSnack = SimpleSnack(view)
    }

    override fun onStart() {
        logger.toConsole("onStart() called")
        super.onStart()
    }

    override fun onResume() {
        logger.toConsole("onResume() called")
        super.onResume()
    }

    override fun onPause() {
        logger.toConsole("onPause() called")
        super.onPause()
    }

    override fun onStop() {
        logger.toConsole("onStop() called")
        super.onStop()
    }

    override fun onDestroyView() {
        logger.toConsole("onDestroyView() called")
        super.onDestroyView()
        _ui = null
    }

    override fun onDestroy() {
        logger.toConsole("onDestroy() called")
        super.onDestroy()
    }
}
