package com.emirtemindarov.tablesapp.games

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun VerticalSlider(sliderPosition: Float, maxRange: Float, setSliderPosition: (Float) -> Unit) {
    Text(text = String.format("%.2f", sliderPosition))
    Column(modifier = Modifier.fillMaxHeight().border(2.dp, Color.Red)) {
        Slider(
            value = sliderPosition,
            onValueChange = { setSliderPosition(it) },
            valueRange = 1f..maxRange,
            //onValueChangeFinished = {},
            modifier = Modifier
                .rotate(-90f)
                .size(40.dp, 600.dp)
                .border(2.dp, Color.Red),
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary
            )
        )
    }
}