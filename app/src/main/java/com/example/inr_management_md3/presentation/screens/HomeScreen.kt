package com.example.inr_management_md3.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inr_management_md3.presentation.theme.INR_Management_Theme
import com.example.inr_management_md3.presentation.theme.nautigalFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Inr Management",
                        fontFamily = nautigalFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.displayMedium
                    )
                }
            )
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Row(
                    modifier = Modifier
                        .weight(1.6f)
                        .padding(top = 16.dp)
                ) {
                    TodayDoseCard()
                }
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 16.dp)
                ) {
                    TomorrowDoseInrCard()
                }
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 16.dp)
                ) {
                    StatisticCard()
                }
            }
        },
        bottomBar = {}
    )
}

@Composable
fun CardsOrdered(modifier: Modifier) {
    Column {
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            modifier = Modifier
                .weight(2f)
        ) {
            TodayDoseCard()
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            modifier = Modifier
                .weight(1f)
        ) {
            TomorrowDoseInrCard()
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            modifier = Modifier
                .weight(1f)
        ) {
            StatisticCard()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodayDoseCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(start = 16.dp, end = 16.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
        elevation = CardDefaults.cardElevation(20.dp)
    ) {
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TomorrowDoseInrCard() {
    Row(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Card(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .height(200.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
            elevation = CardDefaults.cardElevation(20.dp)
        ) {
        }
        Spacer(modifier = Modifier.width(16.dp))

        Card(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .height(200.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
            elevation = CardDefaults.cardElevation(20.dp)
        ) {
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticCard() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(start = 16.dp, end = 16.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiaryContainer),
        elevation = CardDefaults.cardElevation(20.dp)
    ) {
    }
}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mde",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewCardsorderedCard() {
    INR_Management_Theme {
        HomeScreen()
    }
}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mde",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewTodayDoseCard() {
    INR_Management_Theme {
        TodayDoseCard()
    }
}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mde",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewTomorrowDoseCard() {
    INR_Management_Theme {
        TomorrowDoseInrCard()
    }
}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mde",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewStatisticCard() {
    INR_Management_Theme {
        StatisticCard()
    }
}
