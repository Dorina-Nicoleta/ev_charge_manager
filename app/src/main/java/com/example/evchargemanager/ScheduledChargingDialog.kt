package com.example.evchargemanager

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun ScheduledChargingDialog(
    onDismiss: () -> Unit,
    onSave: (Boolean, String, List<String>) -> Unit
) {
    var enabled by remember { mutableStateOf(true) }


    val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    var selectedDays by remember {
        mutableStateOf(setOf("Mon", "Tue", "Wed", "Thu", "Fri"))
    }

    val green = Color(0xFF00A84F)
    val darkText = Color(0xFF111318)
    val secondaryText = Color(0xFF626D7D)
    val lightBackground = Color(0xFFF7F8FA)

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            val dialogWidth = maxWidth * 0.5f
            val dialogHeight = maxHeight * 0.95f

            Card(
                modifier = Modifier
                    .width(dialogWidth)
                    .height(dialogHeight),
                shape = RoundedCornerShape(13.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF0F1F5)
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    // HEADER
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .size(20.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = Color(0xFF59616C),
                                modifier = Modifier.size(17.dp)
                            )
                        }

                        Text(
                            "Scheduled Charging",
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = darkText
                        )
                    }

                    // DESCRIPTION
                    Text(
                        "Schedule when your vehicle starts charging. Charging will begin at the selected time when plugged in.",
                        modifier = Modifier.padding(horizontal = 12.dp),
                        fontSize = 11.sp,
                        lineHeight = 10.sp,
                        color = secondaryText
                    )

                    // ENABLE SCHEDULE CARD
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = lightBackground
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Scheduled Charging",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = darkText
                            )

                            Switch(
                                checked = enabled,
                                onCheckedChange = { enabled = it },
                                colors = androidx.compose.material3.SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = green,
                                    uncheckedThumbColor = Color.White,
                                    uncheckedTrackColor = Color.LightGray
                                )
                            )
                        }
                    }

                    // TIME AND REPEAT CARD
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = lightBackground
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            // TIME SELECTOR
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "Start Charging At",
                                    modifier = Modifier.weight(1f),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = darkText
                                )

                                   // SĂGEȚI SUS
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ){
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                                        ) {
                                            Spacer(Modifier.width(2.dp))
                                            Icon(
                                                Icons.Default.KeyboardArrowUp,
                                                contentDescription = "Increase hour",
                                                modifier = Modifier
                                                    .size(16.dp)
                                                    .clickable {},
                                                tint = Color(0xFF505B6B)
                                            )

                                            Spacer(Modifier.width(5.dp))

                                            Icon(
                                                Icons.Default.KeyboardArrowUp,
                                                contentDescription = "Increase minute",
                                                modifier = Modifier
                                                    .size(16.dp)
                                                    .clickable {},
                                                tint = Color(0xFF505B6B)
                                            )

                                            Spacer(Modifier.width(35.dp))


                                    }

                                    // UN SINGUR BOX GRI
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                Color(0xFFDDE2E9),
                                                RoundedCornerShape(8.dp)
                                            )
                                            .padding(horizontal = 12.dp, vertical = 6.dp)
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                                        ) {
                                            Text("11",
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Medium,
                                                color = Color.Black
                                            )

                                            Text(
                                                ":",
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Medium,
                                                color = Color.Black
                                            )

                                            Text("00",
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Medium,
                                                color = Color.Black
                                            )

                                            Text("PM",
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Medium,
                                                color = Color.Black
                                            )
                                        }
                                    }

                                    // SĂGEȚI JOS
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                                    ){
                                        Spacer(Modifier.width(2.dp))
                                        Icon(
                                            Icons.Default.KeyboardArrowDown,
                                            contentDescription = "Decrease minute",
                                            modifier = Modifier
                                                .size(16.dp)
                                                .clickable {},
                                            tint = Color(0xFF505B6B)
                                        )
                                        Spacer(Modifier.width(5.dp))
                                        Icon(
                                            Icons.Default.KeyboardArrowDown,
                                            contentDescription = "Decrease hour",
                                            modifier = Modifier
                                                .size(16.dp)
                                                .clickable {},
                                            tint = Color(0xFF505B6B)
                                        )
                                        Spacer(Modifier.width(35.dp))
                                    }

                                }
                            }

                            // REPEAT
                            Text(
                                "Repeat",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = darkText
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                days.forEach { day ->
                                    val selected = day in selectedDays

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(horizontal = 3.dp)
                                            .height(30.dp)
                                            .background(
                                                color = if (selected) green
                                                else Color(0xFFDCE1E8),
                                                shape = RoundedCornerShape(50)
                                            )
                                            .clickable { },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            day,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = if (selected) Color.White
                                            else secondaryText
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // GOOD TO KNOW
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = null,
                            tint = secondaryText,
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(Modifier.width(14.dp))

                        Column {
                            Text(
                                "Good to know",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = darkText
                            )

                            Text(
                                "Your vehicle will only start charging at the scheduled time when plugged in. You can still charge manually at any time.",
                                fontSize = 11.sp,
                                lineHeight = 11.sp,
                                color = secondaryText
                            )
                        }
                    }

                    // CANCEL AND SAVE BUTTONS
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(
                            onClick = onDismiss,
                            modifier = Modifier
                                .weight(1f)
                                .height(35.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFD9DEE5),
                                contentColor = darkText
                            )
                        ) {
                            Text(
                                "Cancel",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }

                        Button(
                            onClick = {},
                            modifier = Modifier
                                .weight(1f)
                                .height(35.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF08090B),
                                contentColor = Color.White
                            )
                        )  {
                            Text(
                                "Save",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(widthDp = 1408, heightDp = 792, showBackground = true)
@Composable
fun ScheduleChargingPreview() {
    ScheduledChargingDialog(
        onDismiss = {},
        onSave = { _, _, _ -> }
    )
}