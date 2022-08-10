package com.example.inr_management_md3.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inr_management_md3.data.datamodels.Taking
import com.example.inr_management_md3.data.repository.InrManagementRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

/**
 *  ViewModel for HomeScreen logic
 */
class HomeViewModel(
    private val repository: InrManagementRepository
) : ViewModel() {

    private val _taking = MutableStateFlow(Taking())
    val taking: StateFlow<Taking> get() = _taking

    private val _dateToday = MutableStateFlow("")
    val dateToday: StateFlow<String> get() = _dateToday

    private val _dateTomorrow = MutableStateFlow("")
    val dateTomorrow: StateFlow<String> get() = _dateTomorrow

    /**
     * Initializing
     */
    init {
        viewModelScope.launch(Dispatchers.IO) {
            setTodayDate()
            setTomorrowDate()
        }
    }

    private fun setTodayDate() {
        _dateToday.value = LocalDate.now().format(
            DateTimeFormatter.ofPattern("E, MMM dd. yyyy")
        )
    }

    private fun setTomorrowDate() {
        _dateTomorrow.value = LocalDate.now().plus(1, ChronoUnit.DAYS).format(
            DateTimeFormatter.ofPattern("E, MMM dd. yyyy")
        )
    }
}
