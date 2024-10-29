package ru.dumdumbich.android.steward.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import ru.dumdumbich.android.steward.tools.logger.Logger

abstract class BaseActivity<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : AppCompatActivity() {

    protected lateinit var ui: VB

    // вместо isDebug = true/false передавать значение BuildConfig.Debug - уточнить как это правильно сделать
    protected val logger: Logger by inject { parametersOf(this, true) }

    override fun onCreate(savedInstanceState: Bundle?) {
        logger.toConsole("onCreate() called with: savedInstanceState = $savedInstanceState")
        super.onCreate(savedInstanceState)
        ui = bindingInflater.invoke(layoutInflater)
        setContentView(ui.root)
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

    override fun onDestroy() {
        logger.toConsole("onDestroy() called")
        super.onDestroy()
    }
}
