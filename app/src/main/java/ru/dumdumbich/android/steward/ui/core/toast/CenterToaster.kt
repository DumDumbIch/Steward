package ru.dumdumbich.android.steward.ui.core.toast

import android.content.Context
import ru.dumdumbich.android.steward.databinding.ToastCenterBinding
import ru.dumdumbich.android.steward.ui.base.BaseToaster

class CenterToaster(
    context: Context
) : BaseToaster<ToastCenterBinding>(
    context,
    ToastCenterBinding::inflate
) {
    override fun show(message: String) {
        super.show(message)
        ui.messageToastCenterTextView.text = message
        toast.show()
    }
}
