package com.example.evchargemanager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun ChargingCompleteDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    val darkText = Color(0xFF111318)
    val secondaryText = Color(0xFF626D7D)
    val green = Color(0xFF08A52B)
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
                    }

                    Spacer(Modifier.height(5.dp))

                    // SUCCESS ICON
                    Box(
                        modifier = Modifier
                            .size(78.dp)
                            .background(
                                color = green,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Success",
                            tint = Color.White,
                            modifier = Modifier.size(43.dp)
                        )
                    }

                    Spacer(Modifier.height(17.dp))

                    // TITLE
                    Text(
                        text = "Charging completed",
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        color = darkText,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(11.dp))

                    // DESCRIPTION
                    Text(
                        text = "Your vehicle has reached the set charge limit.\n" +
                                "You can now unplug the charging cable.",
                        fontSize = 14.sp,
                        lineHeight = 19.sp,
                        color = secondaryText,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(23.dp))

                    // ACTION BUTTONS
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(13.dp)
                    ) {

                        // CLOSE BUTTON
                        Button(
                            onClick = onDismiss,
                            modifier = Modifier
                                .weight(1f)
                                .height(45.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = lightGray,
                                contentColor = darkText
                            ),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 0.dp
                            )
                        ) {
                            Text(
                                text = "Close",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        // OK BUTTON
                        Button(
                            onClick = onConfirm,
                            modifier = Modifier
                                .weight(1f)
                                .height(45.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF050608),
                                contentColor = Color.White
                            ),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 0.dp
                            )
                        ) {
                            Text(
                                text = "OK",
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
fun ChargingCompleteDialogPreview() {
    ChargingCompleteDialog(
        onDismiss = {},
        onConfirm = {}
    )
}