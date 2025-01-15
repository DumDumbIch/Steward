package ru.dumdumbich.android.steward.ui.screen.home

import android.view.View
import androidx.core.bundle.Bundle
import androidx.core.bundle.bundleOf
import ru.dumdumbich.android.steward.databinding.FragmentHomeBinding
import ru.dumdumbich.android.steward.ui.base.BaseFragment
import ru.dumdumbich.android.steward.ui.core.scene.Scene
import ru.dumdumbich.android.steward.ui.util.ConvertUnit.dpToPxFloat


class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private var _args: HomeFragmentArgs? = null
    private val args: HomeFragmentArgs get() = _args!!

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        //argsFromBundle()
        _args = arguments?.let { HomeFragmentArgs.fromBundle(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ui.helloHomeFragmentTextView.apply {
            text = "Home Fragment"
            textSize = 72.dpToPxFloat
        }
    }

    companion object {

        fun createScene(args: HomeFragmentArgs) = Scene(
            HomeFragment::class.java,
            tag = "SCENE_HOME",
            args.toBundle()
        )

        fun createScene(args: Bundle? = null) = Scene(
            HomeFragment::class.java,
            tag = "SCENE_HOME",
            args
        )
    }
}
