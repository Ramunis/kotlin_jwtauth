package com.ramunissoft.authjwtapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ramunissoft.authjwtapp.ui.theme.ButtonGradientBlue
import com.ramunissoft.authjwtapp.ui.theme.ButtonGradientPink


@Composable
fun GradientButton(stringId: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp, top = 10.dp)
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        ButtonGradientPink, ButtonGradientBlue
                    )
                ),
                shape = RoundedCornerShape(15.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )
    ) {
        Text(text = stringResource(id = stringId), style = TextStyle(
            color = Color.White,
            fontWeight = FontWeight.Bold
        ))
    }
}