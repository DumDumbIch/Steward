package ru.dumdumbich.android.steward.ui.screen.tune

import android.os.Bundle
import android.view.View
import ru.dumdumbich.android.steward.databinding.FragmentTuneBinding
import ru.dumdumbich.android.steward.ui.base.BaseFragment
import ru.dumdumbich.android.steward.ui.util.ConvertUnit.dpToPxFloat

class TuneFragment : BaseFragment<FragmentTuneBinding>(
    FragmentTuneBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ui.helloTuneFragmentTextView.apply {
            text = "Tune Fragment"
            textSize = 72.dpToPxFloat
        }
    }
}
