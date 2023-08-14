package com.relevanx.tcom

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class PasswordEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {
    private val minPasswordLength = 8

    init {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                error = if ((s?.length ?: 0) < minPasswordLength) {
                    "Password minimal $minPasswordLength karakter"
                } else {
                    null
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}