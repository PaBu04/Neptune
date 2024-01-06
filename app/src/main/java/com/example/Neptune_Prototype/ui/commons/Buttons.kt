package com.example.Neptune_Prototype.ui.commons

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Neptune_Prototype.ui.theme.DefaultButtonColor
import com.example.Neptune_Prototype.ui.theme.DefaultDisabledButtonColor
import com.example.Neptune_Prototype.ui.theme.DefaultDisabledButtonFontColor
import com.example.Neptune_Prototype.ui.theme.PrimaryFontColor
import com.example.Neptune_Prototype.ui.views.modeSelectView.SelectedMode


@Composable
fun StandardButton(onClick: () -> Unit, text: String, enabled: Boolean = true) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = DefaultButtonColor,
            contentColor = PrimaryFontColor,
            disabledContainerColor = DefaultDisabledButtonColor,
            disabledContentColor = DefaultDisabledButtonFontColor
        ),
        enabled = enabled
    ) {
        Text(text, fontSize = 18.sp)
    }
}


@Composable
fun ModeSelectButton(onClick: () -> Unit, text: String, isSelected: Boolean){
    var borderSize = 0.dp
    if(isSelected){
        borderSize = 5.dp
    }
    Button(
        onClick = onClick,
        modifier = Modifier
            .border(borderSize, Color.White, RoundedCornerShape(50))
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = DefaultButtonColor,
            contentColor = PrimaryFontColor,
            disabledContainerColor = DefaultDisabledButtonColor,
            disabledContentColor = DefaultDisabledButtonFontColor
        )
    ) {
        Text(text = text)
    }
}

