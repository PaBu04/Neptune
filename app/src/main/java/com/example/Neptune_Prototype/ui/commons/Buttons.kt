package com.example.Neptune_Prototype.ui.commons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Neptune_Prototype.ui.theme.DefaultButtonColor
import com.example.Neptune_Prototype.ui.theme.DefaultDisabledButtonColor
import com.example.Neptune_Prototype.ui.theme.DefaultDisabledButtonFontColor
import com.example.Neptune_Prototype.ui.theme.PrimaryFontColor


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


@Preview
@Composable
fun StandardButtonPreview() {
    StandardButton({}, "Button")
}

