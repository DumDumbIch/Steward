package ru.dumdumbich.android.steward.ui.screen.tools

import android.os.Bundle
import android.view.View
import ru.dumdumbich.android.steward.databinding.FragmentToolsBinding
import ru.dumdumbich.android.steward.ui.base.BaseFragment
import ru.dumdumbich.android.steward.ui.util.ConvertUnit.dpToPxFloat

class ToolsFragment : BaseFragment<FragmentToolsBinding>(
    FragmentToolsBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ui.helloToolsFragmentTextView.apply {
            text = "Tools Fragment"
            textSize = 72.dpToPxFloat
        }
    }
}
