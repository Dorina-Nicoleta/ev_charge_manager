package com.example.evchargemanager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun StopChargingDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    val darkText = Color(0xFF111318)
    val secondaryText = Color(0xFF626D7D)
    val red = Color(0xFFEA5365)
    val lightGray = Color(0xFFD9DEE5)

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
            val dialogWidth = maxWidth * 0.415f

            Card(
                modifier = Modifier
                    .width(dialogWidth)
                    .wrapContentHeight(),
                shape = RoundedCornerShape(13.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF3F4F7)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 19.dp,
                            vertical = 13.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // HEADER
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp)
                    ) {
                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .size(30.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = Color(0xFF59616C),
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Text(
                            text = "Stop Charging",
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = 19.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = darkText
                        )
                    }

                    Spacer(Modifier.height(18.dp))

                    // CHARGING STOP ICON
                    Box(
                        modifier = Modifier.size(82.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(78.dp)
                                .clip(CircleShape)
                                .background(Color.Transparent)
                        )

                        Box(
                            modifier = Modifier
                                .size(76.dp)
                                .background(
                                    Color.Transparent,
                                    CircleShape
                                )
                        )

                        // Red circular border
                        Surface(
                            modifier = Modifier.size(78.dp),
                            shape = CircleShape,
                            color = Color.Transparent,
                            border = androidx.compose.foundation.BorderStroke(
                                2.dp,
                                red
                            )
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Bolt,
                                    contentDescription = "Charging",
                                    tint = red,
                                    modifier = Modifier.size(43.dp)
                                )
                            }
                        }

                        // Pause indicator
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .offset(x = 4.dp, y = 2.dp)
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE51F35)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Pause,
                                contentDescription = "Pause",
                                tint = Color.White,
                                modifier = Modifier.size(19.dp)
                            )
                        }
                    }

                    Spacer(Modifier.height(18.dp))

                    // CONFIRMATION MESSAGE
                    Text(
                        text = "Are you sure you want to stop charging?",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = darkText,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(7.dp))

                    // DESCRIPTION
                    Text(
                        text = "Charging will stop now. You can resume charging\n" +
                                "at any time by tapping Start Charging.",
                        fontSize = 14.sp,
                        lineHeight = 19.sp,
                        color = secondaryText,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(27.dp))

                    // ACTION BUTTONS
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        // CANCEL
                        Button(
                            onClick = onDismiss,
                            modifier = Modifier
                                .weight(1f)
                                .height(43.dp),
                            shape = RoundedCornerShape(9.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = lightGray,
                                contentColor = darkText
                            ),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 0.dp
                            )
                        ) {
                            Text(
                                text = "Cancel",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        // STOP CHARGING
                        Button(
                            onClick = onConfirm,
                            modifier = Modifier
                                .weight(1f)
                                .height(43.dp),
                            shape = RoundedCornerShape(9.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF050608),
                                contentColor = Color.White
                            ),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 0.dp
                            )
                        ) {
                            Text(
                                text = "Stop Charging",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    widthDp = 1024,
    heightDp = 768,
    showBackground = true
)
@Composable
fun StopChargingDialogPreview() {
    StopChargingDialog(
        onDismiss = {},
        onConfirm = {}
    )
}