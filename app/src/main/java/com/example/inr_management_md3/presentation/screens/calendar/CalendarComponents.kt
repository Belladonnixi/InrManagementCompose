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
package com.example.inr_management_md3.presentation.screens.calendar

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.inr_management_md3.R
import com.example.inr_management_md3.presentation.theme.INR_Management_Theme
import com.example.inr_management_md3.presentation.viewmodel.CalendarViewModel
import org.koin.androidx.compose.inject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarDayView(calendarViewModel: CalendarViewModel) {
    val date by calendarViewModel.date.collectAsState()
    val openPopUp by calendarViewModel.openPopUp.collectAsState()
    val text by calendarViewModel.comment.collectAsState()
    val scrollState = rememberScrollState()
    val iconButtonColor = MaterialTheme.colorScheme.primary

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ElevatedCard(
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Text(
                text = date,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall
            )
            Divider()
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Medicament taken",
                    modifier = Modifier
                        .padding(end = 8.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Start
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.green),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Measure done",
                    modifier = Modifier
                        .padding(end = 8.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Start
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.yellow),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Comment:",
                    modifier = Modifier
                        .padding(end = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(
                            onClick = {
                                calendarViewModel.setUpPopUpState(openPopUp)
                            },
                            enabled = text.isEmpty()
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = null,
                                tint = if (text.isEmpty()) {
                                    iconButtonColor
                                } else {
                                    iconButtonColor.copy(alpha = 0.3f)
                                }
                            )
                        }

                        IconButton(
                            onClick = {
                                calendarViewModel.setUpPopUpState(openPopUp)
                            },
                            enabled = text.isNotEmpty()
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = null,
                                tint = if (text.isNotEmpty()) {
                                    iconButtonColor
                                } else {
                                    iconButtonColor.copy(alpha = 0.3f)
                                }
                            )
                        }
                    }
                }
            }

            if (openPopUp) {
                CommentPopUp(calendarViewModel = calendarViewModel)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .padding(16.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Text(
                    text = text,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .padding(16.dp)
                        .verticalScroll(scrollState),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = { /* Do something! */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(60.dp),
                    enabled = false
                ) { Text("Save") }
            }
        }
    }
}

@Composable
fun CommentPopUp(calendarViewModel: CalendarViewModel) {
    val openPopUp by calendarViewModel.openPopUp.collectAsState()
    val text by calendarViewModel.comment.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 250.dp)
    ) {
        val popupWidth = 360.dp
        val popupHeight = 500.dp
        val cornerSize = 16.dp

        Popup(
            alignment = Alignment.Center,
            properties = PopupProperties(
                focusable = true
            )
        ) {
            Box(
                modifier = Modifier
                    .size(
                        width = popupWidth,
                        height = popupHeight
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(cornerSize)
                    )
                    .border(
                        1.dp,
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    TextField(
                        value = text,
                        onValueChange = { calendarViewModel.setComment(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        maxLines = 13
                    )
                    BoxWithConstraints(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextButton(
                                onClick = {
                                    calendarViewModel.setComment("")
                                    calendarViewModel.setUpPopUpState(openPopUp)
                                }
                            ) {
                                Text(
                                    text = "Cancel",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .padding(start = 32.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.End,
                                verticalArrangement = Arrangement.Center
                            ) {
                                TextButton(
                                    onClick = {
                                        calendarViewModel.setUpPopUpState(openPopUp)
                                    }
                                ) {
                                    Text(
                                        text = "Save",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewMonthViewCalendar() {
    INR_Management_Theme {
        val calendarViewModel: CalendarViewModel by inject()
        CalendarDayView(calendarViewModel)
    }
}
