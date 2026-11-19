package com.example.evchargemanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold(modifier = Modifier.fillMaxSize())
            {
                innerPadding -> ChargingScreen(modifier = Modifier.padding(innerPadding))            }
        }
    }
}

@Composable
fun ChargingScreen(modifier: Modifier = Modifier) {

    var showChargeLimitDialog by remember {
        mutableStateOf(false)
    }

    var showScheduledDialog by remember {
        mutableStateOf(false)
    }

    var showStopDialog by remember {
        mutableStateOf(false)
    }

    var showInterruptDialog by remember {
        mutableStateOf(false)
    }


    var showChargingCompleteDialog by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE9EEF2))
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {

        // 1. CHARGING INFORMATION
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Charging information - LEFT
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    "Charging",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF202830)
                )

                Text(
                    "34 min remaining",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF202830)
                )

                Text(
                    "to reach 80%",
                    fontSize = 12.sp,
                    color = Color(0xFF697782)
                )
            }

            // Battery percentage - CENTER
            Row(
                modifier = Modifier.weight(1f)
                    .clickable {
                        showChargingCompleteDialog = true
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "62",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF202830)
                )

                Text(
                    "%",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF202830)
                )
            }

            // Stop charging button - RIGHT
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {showStopDialog = true},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD4DAE0),
                        contentColor = Color(0xFF202830)
                    )
                ) {
                    Text("Stop Charging")
                }
            }
        }

        // 2. ELECTRIC CAR IMAGE - CENTER
        Image(
            painter = painterResource(
                id = R.drawable.electric_car
            ),
            contentDescription = "Electric car charging",
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
                .clickable {
                    showInterruptDialog = true
                },
            contentScale = ContentScale.Fit
        )

        // 3. CHARGING STATISTICS
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f),
            verticalAlignment = Alignment.CenterVertically
        ) {

            StatItem(
                "125 kW",
                "Charging Power",
                Modifier.weight(1f)
            )

            VerticalDivider(
                modifier = Modifier.height(48.dp),
                color = Color(0xFFCCD3DA)
            )

            StatItem(
                "+28 kWh",
                "Energy Added",
                Modifier.weight(1f)
            )

            VerticalDivider(
                modifier = Modifier.height(48.dp),
                color = Color(0xFFCCD3DA)
            )

            StatItem(
                "34 min",
                "Time Remaining",
                Modifier.weight(1f)
            )

            VerticalDivider(
                modifier = Modifier.height(48.dp),
                color = Color(0xFFCCD3DA)
            )

            StatItem(
                "310 km",
                "Estimated Range ⓘ",
                Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 4. BOTTOM CARDS
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.5f),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            // CARD 1
            Card(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF7F9FB)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    // Charge Limit
                    Row(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showChargeLimitDialog = true
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.AccessTime,
                            contentDescription = null,
                            modifier = Modifier.size(26.dp)
                        )

                        Spacer(Modifier.width(16.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Charge Limit",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                "Set the maximum charge level",
                                fontSize = 11.sp,
                                color = Color.Gray
                            )
                        }

                        Text("80%", fontSize = 14.sp)
                        Icon(Icons.Default.ChevronRight,
                             null,
                             modifier = Modifier.size(20.dp))
                    }

                    HorizontalDivider(color = Color(0xFFE0E5E9))

                    // Scheduled Charging
                    Row(

                        modifier = Modifier.fillMaxWidth().clickable {
                            showScheduledDialog = true
                        },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.CalendarMonth,
                            contentDescription = null,
                            modifier = Modifier.size(26.dp)
                        )

                        Spacer(Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Scheduled Charging",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                "Charge during off-peak hours",
                                fontSize = 11.sp,
                                color = Color.Gray
                            )
                        }

                        Text("Off", fontSize = 14.sp)
                        Icon(
                            Icons.Default.ChevronRight,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

            }

            // CARD 2
            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF7F9FB)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.ShowChart,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(Modifier.width(12.dp))

                        Text(
                            "Charging Session",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )

                        Icon(
                            Icons.Default.ChevronRight,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    HorizontalDivider(color = Color(0xFFE0E5E9))

                    // Session details
                    SessionRow("Session Started", "9:50 AM")
                    SessionRow("Duration", "34 min")
                    SessionRow("Energy Added", "28 kWh")
                    SessionRow("Average Power", "122 kW")
                }
            }
        }

    }

    if (showChargeLimitDialog) {
        ChargeLimitDialog(
            onDismiss = {
                showChargeLimitDialog = false
            },
            onSave = { percent ->

                showChargeLimitDialog = false
            }
        )
    }

    if (showScheduledDialog) {
        ScheduledChargingDialog(
            onDismiss = {
                showScheduledDialog = false
            },
            onSave = { enabled, time, selectedDays ->

                showScheduledDialog = false
            }
        )
    }

    if (showStopDialog) {
        StopChargingDialog(
            onDismiss = {
                showStopDialog = false
            },
            onConfirm = {
                showStopDialog = false

            }
        )
    }

    if (showInterruptDialog) {
        ChargingInterruptedDialog(
            onDismiss = {
                showInterruptDialog  = false
            },
            onConfirm = {
                showInterruptDialog  = false

            }
        )
    }

    if (showChargingCompleteDialog) {
        ChargingCompleteDialog(
            onDismiss = {
                showChargingCompleteDialog = false
            },
            onConfirm = {
                showChargingCompleteDialog = false

            }
        )
    }

}

@Composable
fun SessionRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            color = Color(0xFF697782)
        )

        Text(
            text = value,
            fontSize = 11.sp,
            color = Color(0xFF697782)
        )
    }
}

@Composable
fun StatItem(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            value,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF202830)
        )

        Text(
            label,
            fontSize = 13.sp,
            color = Color(0xFF697782)
        )
    }
}



@Preview(widthDp = 1408, heightDp = 792)
@Composable
fun GreetingPreview() {
    ChargingScreen()
}