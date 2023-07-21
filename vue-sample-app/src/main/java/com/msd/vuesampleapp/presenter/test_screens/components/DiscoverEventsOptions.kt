package com.msd.vuesampleapp.presenter.test_screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.msd.vuesampleapp.repository.EventConfigRepository

@Composable
fun DiscoverEventsOptions() {
    val eventConfigRepository = EventConfigRepository()

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(fontWeight = FontWeight(800), text = "Discover Events")
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Button(onClick = { eventConfigRepository.getConfigData() }) {
            Text(text = "Discover Events")
        }
    }
}