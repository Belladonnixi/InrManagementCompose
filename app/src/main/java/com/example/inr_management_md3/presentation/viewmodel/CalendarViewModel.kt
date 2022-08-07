/**
 * Copyright © 2022 Jessica Ernst
 *
 * This project and source code may use libraries or frameworks that are released under various
 * Open-Source licenses. Use of those libraries and frameworks are governed by their own individual
 * licenses.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.example.inr_management_md3.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inr_management_md3.data.datamodels.Comment
import com.example.inr_management_md3.data.repository.InrManagementRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

/**
 *  ViewModel for CalendarScreens
 *  the calendar itself, calendar dayView and commentDialog
 */

class CalendarViewModel(
    private val repository: InrManagementRepository
) :
    ViewModel() {

    /**
     *  Calendar
     */
    private val _date = MutableStateFlow("")
    val date: StateFlow<String> get() = _date

    private val _realDate = MutableStateFlow<LocalDate?>(null)
    val realDate: StateFlow<LocalDate?> get() = _realDate

    /**
     *  Comment Dialog
     */
    private val _text = MutableStateFlow("")
    val text: StateFlow<String> get() = _text

    private val _comment = MutableStateFlow(Comment())
    val comment: StateFlow<Comment> get() = _comment

    /**
     *  Initializing
     */
    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadData()
        }
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
        }
    }

    /**
     *  Calendar
     */
    fun setDate(setDate: String) {
        _date.value = setDate
    }

    fun setRealDate(setRealDate: LocalDate) {
        _realDate.value = setRealDate
    }

    /**
     *  Comment Dialog
     */
    fun setText(setText: String) {
        _text.value = setText
    }

    fun setComment(commenting: String) {
        _comment.value.commentDay = commenting
        Log.e("comment", comment.value.commentDay)
    }

    fun addCommentToDb() {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.checkIfPatientExists()) {
                repository.getLastPatient().collect { patientId ->
                    _comment.value.patientId = patientId.id_patient
                    _comment.value.commentDate = realDate.value
                    repository.addComment(comment.value)
                    repository.getLastComment().collect { commentId ->
                        if (!repository.checkIfCommentIdIsInPatient(commentId.idComment)) {
                        }
                    }
                }
            } else {
            }
        }
    }
}
