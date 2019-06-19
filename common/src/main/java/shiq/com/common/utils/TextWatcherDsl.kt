package shiq.com.common.utils

import android.text.Editable
import android.text.TextWatcher

/**
 * 监听EditText的dsl类
 * created by shi on 2019/6/12/012
 *
 */
class TextWatcherDsl : TextWatcher {
    private var afterTextChanged: ((s: Editable?) -> Unit)? = null
    private var beforeTextChanged: ((s: CharSequence?, start: Int, count: Int, after: Int) -> Unit)? =
        null
    private var onTextChanged: ((s: CharSequence?, start: Int, before: Int, count: Int) -> Unit)? =
        null

    override fun afterTextChanged(s: Editable?) {
        afterTextChanged?.invoke(s)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        beforeTextChanged?.invoke(s, start, count, after)
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChanged?.invoke(s, start, before, count)
    }

    fun afterTextChanged(after: (s: Editable?) -> Unit) {
        afterTextChanged = after
    }

    fun beforeTextChanged(before: (s: CharSequence?, start: Int, count: Int, after: Int) -> Unit) {
        beforeTextChanged = before
    }

    fun onTextChanged(onChanged: (s: CharSequence?, start: Int, before: Int, count: Int) -> Unit) {
        onTextChanged = onChanged
    }
}

