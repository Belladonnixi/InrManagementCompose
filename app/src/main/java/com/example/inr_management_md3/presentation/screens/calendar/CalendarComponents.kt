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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.inr_management_md3.R
import com.example.inr_management_md3.presentation.components.WriteCommentDialog
import com.example.inr_management_md3.presentation.viewmodel.CalendarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarDayView(calendarViewModel: CalendarViewModel) {
    val date by calendarViewModel.date.collectAsState()
    val text by calendarViewModel.text.collectAsState()
    val comment by calendarViewModel.comment.collectAsState()
    val realDate by calendarViewModel.realDate.collectAsState()
    val scrollState = rememberScrollState()
    val iconButtonColor = MaterialTheme.colorScheme.primary
    val dialogState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

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
                                dialogState.value = true
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
                                dialogState.value = true
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

            if (dialogState.value) {
                WriteCommentDialog(
                    title = "WRITE COMMENT",
                    dialogState = dialogState,
                    text = text,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp),
                    onValueChange = { calendarViewModel.setText(it) },
                    maxLinesComment = 12,
                    cancelButton = { dialogState.value = false },
                    okButton = {
                        calendarViewModel.setComment(text)
//                        calendarViewModel.addCommentToDb()
                        dialogState.value = false
                    }
                )
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
                    text = if (realDate == comment.commentDate) {
                        calendarViewModel.setText(comment.commentDay)
                        text
                    } else {
                        text
                    },
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
