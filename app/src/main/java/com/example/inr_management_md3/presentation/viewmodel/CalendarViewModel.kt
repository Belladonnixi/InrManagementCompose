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

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate

class CalendarViewModel :
    ViewModel() {
    private val _date = MutableStateFlow("")
    val date: StateFlow<String> get() = _date

    private val _realDate = MutableStateFlow<LocalDate?>(null)
    val realDate: StateFlow<LocalDate?> get() = _realDate

    private val _openPopUp = MutableStateFlow(false)
    val openPopUp: StateFlow<Boolean> get() = _openPopUp

    private val _comment = MutableStateFlow("")
    val comment: StateFlow<String> get() = _comment

    fun setDate(setDate: String) {
        _date.value = setDate
    }

    fun setRealDate(setRealDate: LocalDate) {
        _realDate.value = setRealDate
    }

    fun setUpPopUpState(setPopUp: Boolean) {
        _openPopUp.value = !setPopUp
    }

    fun setComment(setText: String) {
        _comment.value = setText
    }
}
