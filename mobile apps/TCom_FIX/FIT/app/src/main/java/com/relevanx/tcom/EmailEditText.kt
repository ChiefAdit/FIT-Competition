package com.relevanx.tcom

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import androidx.appcompat.widget.AppCompatEditText

class EmailEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    init {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val emailPattern = Patterns.EMAIL_ADDRESS
                error = if (!emailPattern.matcher(text.toString()).matches()) {
                    "Format email salah"
                } else {
                    null
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}