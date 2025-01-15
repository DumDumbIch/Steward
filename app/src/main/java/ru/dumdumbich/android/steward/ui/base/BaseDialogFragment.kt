package ru.dumdumbich.android.steward.ui.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import ru.dumdumbich.android.steward.tools.logger.Logger


@Suppress("TooManyFunctions")
abstract class BaseDialogFragment<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : BottomSheetDialogFragment(), KoinComponent {

    protected val logger: Logger by inject { parametersOf(this, true) }

    private var _ui: VB? = null
    protected val ui get() = _ui ?: error("Binding of ${this::class.java.simpleName} is null")

    /**
     * DialogFragment
     */


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        logger.toConsole("onCreateDialog() called with: savedInstanceState = $savedInstanceState")
        return super.onCreateDialog(savedInstanceState)
    }

    override fun dismiss() {
        logger.toConsole("dismiss() called")
        super.dismiss()
    }

    /**
     * Fragment
     */

    /**
     * On attach
     *
     * @param context
     */
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
    ): View {
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
