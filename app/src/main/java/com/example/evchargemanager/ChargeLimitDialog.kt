package com.example.evchargemanager

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun ChargeLimitDialog(
    onDismiss: () -> Unit,
    onSave: (String) -> Unit
) {
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
            modifier = Modifier.fillMaxSize().padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {

            val dialogWidth = (maxWidth * 0.48f)
            val dialogHeight = maxHeight * 0.96f

            Card(
                modifier = Modifier
                    .width(dialogWidth)
                    .height(dialogHeight),
                shape = RoundedCornerShape(13.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF0F1F5)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
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
                            "Charge Limit",
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = darkText
                        )
                    }

                    // DESCRIPTION
                    Text(
                        "Set the maximum charge level for your battery. " +
                                "Charging will stop automatically when the selected limit is reached.",
                        modifier = Modifier.padding(horizontal = 12.dp),
                        fontSize = 11.sp,
                        lineHeight = 11.sp,
                        color = secondaryText
                    )

                    // SELECTED CHARGE VALUE
                    Column(
                        modifier = Modifier.padding(
                            start = 12.dp,
                            top = 4.dp
                        )
                    ) {
                        Text(
                            "80%",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = darkText
                        )

                        Text(
                            "Recommended for daily use",
                            fontSize = 11.sp,
                            color = secondaryText
                        )
                    }

                    // STATIC SLIDER
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                    ) {

                        BoxWithConstraints(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(18.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            val sliderWidth = maxWidth

                            // Background track
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(6.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFD0D4DC))
                            )

                            // Active track (50% - 80%)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.60f)
                                    .height(6.dp)
                                    .clip(CircleShape)
                                    .background(green)
                            )

                            // Vertical tick marks
                            for (i in 0..5) {
                                Box(
                                    modifier = Modifier
                                        .offset(x = sliderWidth * (i / 5f) - 0.5.dp)
                                        .width(1.dp)
                                        .height(10.dp)
                                        .align(Alignment.BottomStart)
                                        .background(Color(0xFF9BA1AC))
                                )
                            }

                            // Slider thumb at 80%
                            Box(
                                modifier = Modifier
                                    .offset(x = sliderWidth * 0.60f - 10.dp)
                                    .size(15.dp)
                                    .background(Color.Black, CircleShape)
                                    .padding(4.dp)
                                    .background(
                                        Color.White,
                                        CircleShape
                                    )
                            )
                        }

                        Spacer(Modifier.height(4.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            listOf("50%", "60%", "70%", "80%", "90%", "100%")
                                .forEach { value ->
                                    Text(
                                        value,
                                        fontSize = 10.sp,
                                        fontWeight = if (value == "80%")
                                            FontWeight.Bold
                                        else FontWeight.Normal,
                                        color = if (value == "80%")
                                            darkText
                                        else secondaryText
                                    )
                                }
                        }
                    }

                    // CHARGE OPTIONS CARD
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = lightBackground
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 20.dp)
                        ) {

                            ChargeOptionUI(
                                title = "50–80%",
                                subtitle = "Recommended for daily driving",
                                selected = true,
                                badge = "Best for battery health"
                            )

                            HorizontalDivider(
                                color = Color(0xFFDCE0E6)
                            )

                            ChargeOptionUI(
                                title = "90%",
                                subtitle = "Recommended for long trips",
                                selected = false
                            )

                            HorizontalDivider(
                                color = Color(0xFFDCE0E6)
                            )

                            ChargeOptionUI(
                                title = "100%",
                                subtitle = "Use for maximum range (e.g., before a road trip)",
                                selected = false
                            )
                        }
                    }

                    // GOOD TO KNOW
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = null,
                            tint = secondaryText,
                            modifier = Modifier.size(27.dp)
                        )

                        Spacer(Modifier.width(12.dp))

                        Text(
                            "Keeping your charge limit at 80% can help extend battery lifespan over time.",
                            fontSize = 11.sp,
                            lineHeight = 11.sp,
                            color = secondaryText
                        )
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
                            onClick = {} ,
                            modifier = Modifier
                                .weight(1f)
                                .height(35.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF08090B),
                                contentColor = Color.White
                            )
                        ) {
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


// CHARGE OPTION COMPONENT
@Composable
fun ChargeOptionUI(
    title: String,
    subtitle: String,
    selected: Boolean,
    badge: String? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // TITLE AND SUBTITLE
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111318)
            )

            Text(
                subtitle,
                fontSize = 10.sp,
                lineHeight = 14.sp,
                color = Color(0xFF626D7D)
            )
        }

        // BADGE
        if (badge != null) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(
                        horizontal = 10.dp,
                        vertical = 6.dp
                    )
            ) {
                Text(
                    badge,
                    fontSize = 10.sp,
                    color = Color(0xFF20A968),

                )
            }

            Spacer(Modifier.width(12.dp))
        }

        // RADIO BUTTON
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(Color.White,
                    shape = CircleShape
                )
                .border(
                    width = 1.5.dp,
                    color = Color.Black,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(if (selected) 8.dp else 0.dp)
                    .background(Color.Black, shape = CircleShape)
            )
        }
    }
}

@Preview(widthDp = 1408, heightDp = 792, showBackground = true)
@Composable
fun ChargeLimitDialogPreview() {
    ChargeLimitDialog(
        onDismiss = {},
        onSave = { _, ->}
    )
}