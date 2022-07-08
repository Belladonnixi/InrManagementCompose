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
package com.example.inr_management_md3.presentation.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.inr_management_md3.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsMenu(navController: NavController) {
    ElevatedCard(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Medicament",
                textAlign = TextAlign.Start
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = { navController.navigate(Screens.SetMedicament.route) },
                ) {
                    Icon(
                        Icons.Filled.ChevronRight,
                        contentDescription = null
                    )
                }
            }
        }
        Divider(color = MaterialTheme.colorScheme.onSurfaceVariant, thickness = 2.dp)

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Target Range",
                textAlign = TextAlign.Start
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = { navController.navigate(Screens.SetTargetRange.route) },
                ) {
                    Icon(
                        Icons.Filled.ChevronRight,
                        contentDescription = null
                    )
                }
            }
        }
        Divider(color = MaterialTheme.colorScheme.onSurfaceVariant, thickness = 2.dp)

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "INR Measure",
                textAlign = TextAlign.Start
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = { navController.navigate(Screens.SetMeasure.route) },
                ) {
                    Icon(
                        Icons.Filled.ChevronRight,
                        contentDescription = null
                    )
                }
            }
        }
        Divider(color = MaterialTheme.colorScheme.onSurfaceVariant, thickness = 2.dp)

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "About",
                textAlign = TextAlign.Start
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = { navController.navigate(Screens.About.route) },
                ) {
                    Icon(
                        Icons.Filled.ChevronRight,
                        contentDescription = null
                    )
                }
            }
        }
    }
}
