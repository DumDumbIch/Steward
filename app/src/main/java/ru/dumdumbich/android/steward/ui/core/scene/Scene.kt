package ru.dumdumbich.android.steward.ui.core.scene

import androidx.core.bundle.Bundle
import androidx.core.bundle.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import ru.dumdumbich.android.steward.ui.base.BaseScene

/**
 * Scene
 *
 * @constructor Create empty Scene
 */
class Scene(
    fragmentClass: Class<out Fragment>,
    tag: String? = null,
    args: Bundle? = null  // Pair<String, Any>? = null
) : BaseScene() {

    private var _fragmentClass: Class<out Fragment>? = null
    private val fragmentClass: Class<out Fragment> get() = _fragmentClass!!

    private var _fragmentManager: FragmentManager? = null
    private val fragmentManager: FragmentManager get() = _fragmentManager!!

    private var _containerLayout: Int? = null
    private val containerLayout: Int get() = _containerLayout!!

    private var _tag: String? = null
    private val tag: String get() = _tag ?: ""

    private var _args: Bundle? = null  // Pair<String, Any?>? = null
    private val args: Bundle
        get() = _args ?: bundleOf("" to "")  // Pair<String, Any?> get() = _args ?: ("" to "")

    private var _isVisible: Boolean = false


    init {
        _fragmentClass = fragmentClass
        _tag = tag
        _args = args
    }

    fun install(
        manager: FragmentManager,
        container: Int
    ) {
        _fragmentManager = manager
        _containerLayout = container
        fragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.animator.fade_in,
                android.R.animator.fade_out
            )
            .replace(
                containerLayout,
                fragmentClass,
                args,
                tag
            )
            .commit()
        _isVisible = true
    }

    fun remove() {
        val fragment = fragmentManager.findFragmentByTag(tag)
        if ((fragment != null)) {
            fragmentManager.commit {
                remove(fragment)
            }
        }
    }

    fun destroy() {
        remove()
        _fragmentClass = null
        _tag = null
        _args = null
        _fragmentManager = null
        _containerLayout = null
    }

    fun show() {
        val fragment = fragmentManager.findFragmentByTag(tag)
        fragmentManager.commit {
            if (fragment != null) {
                show(fragment)
                _isVisible = true
            }
        }
    }

    fun hide() {
        val fragment = fragmentManager.findFragmentByTag(tag)
        fragmentManager.commit {
            if (fragment != null) {
                hide(fragment)
                _isVisible = false
            }
        }
    }
}
