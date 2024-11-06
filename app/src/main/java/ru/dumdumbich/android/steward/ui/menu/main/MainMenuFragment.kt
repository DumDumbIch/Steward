package ru.dumdumbich.android.steward.ui.menu.main

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.dumdumbich.android.steward.databinding.FragmentMenuMainBinding
import ru.dumdumbich.android.steward.ui.base.BaseFragment

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
}
