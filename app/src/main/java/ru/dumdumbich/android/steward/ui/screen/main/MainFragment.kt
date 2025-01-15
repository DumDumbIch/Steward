package ru.dumdumbich.android.steward.ui.screen.main

import android.view.View
import androidx.core.bundle.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.dumdumbich.android.steward.databinding.FragmentMainBinding
import ru.dumdumbich.android.steward.domain.usecase.navigation.NavigationEventUseCase
import ru.dumdumbich.android.steward.domain.usecase.sms.ReceivedSmsEventsUseCase
import ru.dumdumbich.android.steward.ui.base.BaseFragment
import ru.dumdumbich.android.steward.ui.core.scene.Scene
import ru.dumdumbich.android.steward.ui.navigation.MainNavigator
import ru.dumdumbich.android.steward.ui.util.ConvertUnit.dpToPx
import ru.dumdumbich.android.steward.ui.util.ConvertUnit.dpToPxFloat


@Suppress("TooManyFunctions")
class MainFragment : BaseFragment<FragmentMainBinding>(
    FragmentMainBinding::inflate
) {

    private val viewModel by viewModel<MainViewModel>()
    private val navigator: MainNavigator by inject()

    private var _sceneMain: Scene? = null
    private val sceneMain: Scene get() = _sceneMain!!

    private val receivedSmsHandler: ReceivedSmsEventsUseCase by inject()
    private val navigationHandler: NavigationEventUseCase by inject()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        runReceivedSmsHandler()
        runNavigationHandler()
        runUiActionHandler()
        initUi()
        putViewStateInViewModel()
    }

    private fun runReceivedSmsHandler() {
        receivedSmsHandler(
            performIfEmptyMessageWasReceived = {},
            performIfTextSmsMessageWasReceived = { text ->
                logger.toConsole("Received text SMS : $text")
            },
            performIfDataSmsMessageWasReceived = { data ->
                logger.toConsole("Received data SMS : $data")
            }
        )
    }

    private fun runNavigationHandler() {
        navigationHandler(
            navigateEmpty = {
                logger.toConsole("navigationHandler : navigateEmpty")
            },
            navigateToHomeScreen = { args ->
                logger.toConsole("navigationHandler : navigateToHomeScreen (args = $args)")
                _sceneMain = navigator.createHomeScene(args)
                showScene()
            },
            navigateToTuneScreen = { args ->
                logger.toConsole("navigationHandler : navigateToTuneScreen (args = $args)")
                _sceneMain = navigator.createTuneScene(args)
                showScene()
            },
            navigateToToolsScreen = { args ->
                logger.toConsole("navigationHandler : navigateToToolsScreen (args = $args)")
                _sceneMain = navigator.createToolsScene(args)
                showScene()
            }
        )
    }

    private fun runUiActionHandler() {
        viewModel.viewAction.onEach { action ->
            logger.toConsole("Action = $action")
            when (action) {
                MainViewAction.HideProgress -> hideProgress()
                MainViewAction.Idle -> Unit
                MainViewAction.ShowProgress -> showProgress()
                MainViewAction.SwitchProgress -> switchProgress()
                is MainViewAction.PutViewState -> setUiByViewState(action.viewState)
            }
            putViewStateInViewModel()
            viewModel.clearAction()
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initUi() {
        ui.helloMainFragmentTextView.apply {
            text = "Main Fragment"
            textSize = 24.dpToPxFloat
        }

        ui.mainFragmentButton.apply {
            text = "Change scene"
            textSize = 24.dpToPxFloat
            cornerRadius = 16.dpToPx
            setOnClickListener {
                viewModel.onClickMainButton()
                simpleToaster.show("Hello from Center Toaster Main Fragment")
            }
        }

        ui.mainFragmentProgressBar.apply {
            indicatorSize = 72.dpToPx
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sceneMain.destroy()
        _sceneMain = null
    }

    private fun showScene() {
        sceneMain.install(childFragmentManager, ui.mainFragmentContainer.id)
    }

    private fun putViewStateInViewModel() {
        viewModel.getViewStateFromUi(
            MainViewState(
                isShowProgress = ui.mainFragmentProgressBar.isVisible,
                sceneTitle = ui.helloMainFragmentTextView.text.toString()
            )
        )
    }

    private fun setUiByViewState(viewState: MainViewState) {
        ui.mainFragmentProgressBar.isVisible = viewState.isShowProgress
        ui.helloMainFragmentTextView.text = viewState.sceneTitle
    }

    private fun showProgress() {
        ui.mainFragmentProgressBar.isVisible = true
    }

    private fun hideProgress() {
        ui.mainFragmentProgressBar.isVisible = false
    }

    private fun switchProgress() {
        val isShowProgress = ui.mainFragmentProgressBar.isVisible
        ui.mainFragmentProgressBar.isVisible = !isShowProgress
    }

    companion object {
        fun createScene(args: Bundle? = null) = Scene(
            MainFragment::class.java,
            tag = "SCENE_MAIN",
            args
        )
    }
}
