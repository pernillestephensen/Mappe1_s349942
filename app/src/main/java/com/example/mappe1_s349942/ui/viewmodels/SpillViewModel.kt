package com.example.mappe1_s349942.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.mappe1_s349942.R
import kotlin.random.Random

class SpillViewModel(app: Application) : AndroidViewModel(app) {

    private val alleOppgaver: List<String> =
        app.resources.getStringArray(R.array.oppgaver).toList()
    private val alleSvar: List<String> =
        app.resources.getStringArray(R.array.svar).toList()

    private val _spmIndeks = mutableStateOf(0)
    val spmIndeks: State<Int> = _spmIndeks

    private val _oppgaveTekst = mutableStateOf("")
    val oppgaveTekst: State<String> = _oppgaveTekst

    private val _inputTekst = mutableStateOf("")
    val inputText: State<String> = _inputTekst

    private val _tilbakemelding = mutableStateOf<String?>(null)
    val tilbakemelding: State<String?> = _tilbakemelding

    private val _spillOver = mutableStateOf(false)
    val spillOver: State<Boolean> = _spillOver

    private val _avsluttetManuelt = mutableStateOf(false)
    val avsluttetManuelt: State<Boolean> = _avsluttetManuelt

    private var selectedIndices: MutableList<Int> = mutableListOf()
    private var sessionSize: Int = 5
    private val _riktige = mutableStateOf(0)
    val riktige: State<Int> = _riktige

    fun startSpill(preferredSize: Int) {
        sessionSize = preferredSize.coerceIn(1, alleOppgaver.size)
        _spillOver.value = false
        _tilbakemelding.value = null
        _inputTekst.value = ""
        _spmIndeks.value = 0
        _riktige.value = 0

        selectedIndices = (alleOppgaver.indices)
            .shuffled(Random(System.currentTimeMillis()))
            .take(sessionSize)
            .toMutableList()
        finnSpm()
    }

    private fun finnSpm() {
        if (_spmIndeks.value >= selectedIndices.size) {
            _spillOver.value = true
            _oppgaveTekst.value = ""
            return
        }
        val idx = selectedIndices[_spmIndeks.value]
        _oppgaveTekst.value = alleOppgaver[idx]
        _inputTekst.value = ""
        _tilbakemelding.value = null
    }

    fun leggTilTall(d: Int) {
        if (_spillOver.value) return
        // begrens lengde (f. eks. maks 3 siffer)
        if (_inputTekst.value.length >= 3) return
        _inputTekst.value += d.toString()
    }

    fun slettTall() {
        if (_spillOver.value) return
        if (_inputTekst.value.isNotEmpty()) {
            _inputTekst.value = _inputTekst.value.dropLast(1)
        }
    }

    fun sendInnSvar() {
        if (_spillOver.value) return
        val user = _inputTekst.value
        if (user.isBlank()) {
            _tilbakemelding.value = "tomt"
            return
        }
        val idx = selectedIndices[_spmIndeks.value]
        val correct = alleSvar[idx]

        if (user == correct) {
            _tilbakemelding.value = "riktig"
            _riktige.value = _riktige.value + 1
        } else {
            _tilbakemelding.value = "feil:$correct"
        }
    }

    fun nesteSpm() {
        // hvis siste oppgave var vist og svar sjekket -> gå til neste
        _spmIndeks.value = _spmIndeks.value + 1
        if (_spmIndeks.value >= selectedIndices.size) {
            _spillOver.value = true
            _oppgaveTekst.value = ""
        } else {
            finnSpm()
        }
    }

    fun avsluttSpill() {
        _spillOver.value = true
        _avsluttetManuelt.value = true
    }

    // Returnerer antall gjenværende oppgaver
    fun remaining(): Int {
        return (selectedIndices.size - _spmIndeks.value)
    }
}
