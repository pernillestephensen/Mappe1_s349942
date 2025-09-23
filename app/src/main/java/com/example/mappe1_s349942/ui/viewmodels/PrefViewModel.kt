package com.example.mappe1_s349942.ui.viewmodels

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application

class PrefViewModel (application: Application): AndroidViewModel(application){
    fun settPref(tekst: String) {
        val sharedPreferences: SharedPreferences =
            application.getSharedPreferences("Mine Preferanser" ,
                android.content.Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("verdi", tekst)
        editor.apply()
    }
    fun hentPref(): String? {
        val sharedPreferences: SharedPreferences =
            application.getSharedPreferences("Mine Preferanser" ,
                android.content.Context.MODE_PRIVATE)
        return sharedPreferences.getString("verdi", "")
    }
}