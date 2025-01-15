package ru.dumdumbich.android.steward.ui.menu.main

import android.view.View
import androidx.core.bundle.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.dumdumbich.android.steward.databinding.FragmentMenuMainBinding
import ru.dumdumbich.android.steward.ui.base.BaseFragment
import ru.dumdumbich.android.steward.ui.core.scene.Scene


class MainMenuFragment : BaseFragment<FragmentMenuMainBinding>(
    FragmentMenuMainBinding::inflate
) {

    private val viewModel by viewModel<MainMenuViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ui.homeMenuItemTextView.setOnClickListener {
            viewModel.onPressHomeMenuItem()
        }
        ui.tuneMenuItemTextView.setOnClickListener {
            viewModel.onPressTuneMenuItem()
        }
        ui.toolsMenuItemTextView.setOnClickListener {
            viewModel.onPressToolsMenuItem()
        }
    }

    companion object {
        fun createScene(args: Bundle? = null) = Scene(
            MainMenuFragment::class.java,
            tag = "MENU_MAIN",
            args
        )
    }
}
