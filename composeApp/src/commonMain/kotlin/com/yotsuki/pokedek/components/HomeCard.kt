package com.yotsuki.pokedek.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeCard(
    backgroundColor: Color,
    icon: DrawableResource,
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(160.dp, 90.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        backgroundColor = backgroundColor,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Text
            Text(
                text = text,
                style = MaterialTheme.typography.body1.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    )
            )
            // Icon
            Icon(
                painter = painterResource(icon),
                contentDescription = text,
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}