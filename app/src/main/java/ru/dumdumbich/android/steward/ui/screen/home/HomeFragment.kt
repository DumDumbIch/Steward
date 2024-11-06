package ru.dumdumbich.android.steward.ui.screen.home

import android.os.Bundle
import android.view.View
import ru.dumdumbich.android.steward.databinding.FragmentHomeBinding
import ru.dumdumbich.android.steward.ui.base.BaseFragment
import ru.dumdumbich.android.steward.ui.util.ConvertUnit.dpToPxFloat

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ui.helloHomeFragmentTextView.apply {
            text = "Home Fragment"
            textSize = 72.dpToPxFloat
        }
    }
}
