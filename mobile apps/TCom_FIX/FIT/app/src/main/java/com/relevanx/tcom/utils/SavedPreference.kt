package com.relevanx.tcom.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object SavedPreference {
    const val UID = "uid"

    private  fun getSharedPreference(ctx: Context?): SharedPreferences? {
        return PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    private fun editor(context: Context, const:String, string: String){
        getSharedPreference(
            context
        )?.edit()?.putString(const,string)?.apply()
    }

    fun setUid(context: Context, uid:String){
        editor(
            context,
            UID,
            uid
        )
    }

    fun getUid(context: Context) = getSharedPreference(
        context
    )?.getString(UID,"")
}