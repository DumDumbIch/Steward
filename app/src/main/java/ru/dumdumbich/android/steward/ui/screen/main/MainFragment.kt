package ru.dumdumbich.android.steward.ui.screen.main

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.dumdumbich.android.steward.databinding.FragmentMainBinding
import ru.dumdumbich.android.steward.ui.base.BaseFragment
import ru.dumdumbich.android.steward.ui.core.scene.Scene
import ru.dumdumbich.android.steward.ui.navigation.Navigation
import ru.dumdumbich.android.steward.ui.navigation.NavigationAction
import ru.dumdumbich.android.steward.ui.util.ConvertUnit.dpToPx
import ru.dumdumbich.android.steward.ui.util.ConvertUnit.dpToPxFloat

class MainFragment : BaseFragment<FragmentMainBinding>(
    FragmentMainBinding::inflate
) {

    private val viewModel by viewModel<MainViewModel>()
    private val navigator: Navigation by inject()

    private var _scene: Scene? = null
    private val scene: Scene get() = _scene!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigator.navigationAction.onEach { action ->
            logger.toConsole("Navigation action = $action")
            when (action) {
                NavigationAction.Idle -> Unit
                is NavigationAction.ShowHomeScene,
                is NavigationAction.ShowToolsScene,
                is NavigationAction.ShowTuneScene -> showScene(action.scene)
            }
            navigator.clearAction()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

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
            }
        }

        ui.mainFragmentProgressBar.apply {
            indicatorSize = 72.dpToPx
        }

//        _scene = Scene(
//            HomeFragment::class.java,
//            "SCENE_HOME",
//            null
//        )
//        scene.install(parentFragmentManager, ui.mainFragmentContainer.id)
        putViewStateInViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        scene.destroy()
        _scene = null
    }

    private fun showScene(scene: Scene) {
        _scene = scene
        scene.install(parentFragmentManager, ui.mainFragmentContainer.id)
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
}
