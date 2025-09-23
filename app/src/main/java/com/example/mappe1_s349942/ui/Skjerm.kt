package com.example.mappe1_s349942.ui

sealed class Skjerm (val rute: String) {
    object Start: Skjerm("startSkjerm")
    object Spill: Skjerm("startSpillet")
    object OmSpillet: Skjerm("omSpillet")
    object Preferanser: Skjerm("preferanser")
}