package com.msd.vuesampleapp.presenter.test_screens.test_screen_2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.msd.vuesampleapp.presenter.test_screens.components.DiscoverEventsOptions
import com.msd.vuesampleapp.presenter.test_screens.components.RecommendationCarousel
import com.msd.vuesampleapp.presenter.test_screens.components.RecommendationOptionColumn
import com.msd.vuesampleapp.presenter.util.Screen
import com.msd.vuesampleapp.repository.FetchRecommendationRepository

@Preview
@Composable
fun TestScreen2(navController: NavController? = null) {
    val fetchRecommendationRepository = FetchRecommendationRepository()
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxHeight().verticalScroll(scrollState), verticalArrangement = Arrangement.SpaceBetween) {
        Column(modifier = Modifier.fillMaxWidth()) {
            RecommendationOptionColumn(fetchRecommendationRepository)
            RecommendationCarousel(fetchRecommendationRepository.recommendationState)
            DiscoverEventsOptions()
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Button(onClick = {
                navController?.navigate(Screen.TestingScreen1.Route)
            }) {
                Text(text = "<- Page 1")
            }
        }
    }
}