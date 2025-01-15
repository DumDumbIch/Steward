package ru.dumdumbich.android.steward.ui.core.dialog

import android.os.Bundle
import android.view.View
import ru.dumdumbich.android.steward.databinding.FragmentDialogConfirmationBinding
import ru.dumdumbich.android.steward.ui.base.BaseDialogFragment


class ConfirmationDialogFragment : BaseDialogFragment<FragmentDialogConfirmationBinding>(
    FragmentDialogConfirmationBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        const val TAG = "ConfirmationDialogFragment"
    }
}
