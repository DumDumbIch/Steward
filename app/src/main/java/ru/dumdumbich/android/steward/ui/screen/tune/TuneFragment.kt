package ru.dumdumbich.android.steward.ui.screen.tune

import android.view.View
import androidx.core.bundle.Bundle
import ru.dumdumbich.android.steward.databinding.FragmentTuneBinding
import ru.dumdumbich.android.steward.ui.base.BaseFragment
import ru.dumdumbich.android.steward.ui.core.scene.Scene
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

    companion object {
        fun createScene(args: Bundle? = null) = Scene(
            TuneFragment::class.java,
            tag = "SCENE_TUNE",
            args
        )
    }
}
