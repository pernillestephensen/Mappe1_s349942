package com.example.mappe1_s349942.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

private const val PREFS_NAME = "matte_prefs"
private const val KEY_ANTALL = "antall_sporsmal"

class PrefViewModel(application: Application) : AndroidViewModel(application) {
    private val prefs = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val _antall = mutableStateOf(prefs.getInt(KEY_ANTALL, 5))
    val antall: State<Int> = _antall

    fun settAntall(n: Int) {
        prefs.edit().putInt(KEY_ANTALL, n).apply()
        _antall.value = n
    }
}
