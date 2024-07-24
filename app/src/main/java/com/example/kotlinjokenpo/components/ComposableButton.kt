package com.example.kotlinjokenpo.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ComposableButton(
    iconName: ImageVector? = null,
    buttonLabel: String,
    buttonColor: Color,
    onClick: () -> Unit,
    borderColor: Color? = null,
    content: @Composable () -> Unit = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (iconName != null) {
                Icon(
                    imageVector = iconName,
                    contentDescription = "Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = buttonLabel,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
        }
    }
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(72.dp)
            .padding(horizontal = 8.dp)
            .border(
                width = if (borderColor != null) 2.dp else 0.dp,
                color = borderColor ?: Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = Color.White
        )
    ) {
        content()
    }
}