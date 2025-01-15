package ru.dumdumbich.android.steward.ui.screen.home

import androidx.core.bundle.Bundle
import androidx.core.bundle.bundleOf


data class HomeFragmentArgs(
    val title: String,
    val description: String
) {

    companion object {
        private const val ARG_TITLE = "HomeFragmentArgs_Title"
        private const val ARG_DESCRIPTION = "HomeFragmentArgs_Description"

        fun fromBundle(bundle: Bundle): HomeFragmentArgs {
            return HomeFragmentArgs(
                title = bundle.getString(ARG_TITLE) ?: "",
                description = bundle.getString(ARG_DESCRIPTION) ?: "",
            )
        }

    }

    fun toBundle(): Bundle {
        return bundleOf(
            ARG_TITLE to this.title,
            ARG_DESCRIPTION to this.description
        )
    }
}
