package com.msd.vuesampleapp.presenter.test_screens.test_screen_1

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
import com.msd.vuesampleapp.presenter.test_screens.components.RecommendationCarousel
import com.msd.vuesampleapp.presenter.test_screens.components.RecommendationOptionColumn
import com.msd.vuesampleapp.presenter.test_screens.components.TrackingOptions1
import com.msd.vuesampleapp.presenter.test_screens.components.TrackingOptions2
import com.msd.vuesampleapp.presenter.util.Screen
import com.msd.vuesampleapp.repository.FetchRecommendationRepository

@Preview
@Composable
fun TestScreen1(navController: NavController? = null) {
    val fetchRecommendationRepository = FetchRecommendationRepository()
    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxHeight()
        .verticalScroll(scrollState), verticalArrangement = Arrangement.SpaceBetween) {
        Column(modifier = Modifier.fillMaxWidth()) {
            RecommendationOptionColumn(fetchRecommendationRepository)
            TrackingOptions1()
            TrackingOptions2()
            RecommendationCarousel(fetchRecommendationRepository.recommendationState)
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Button(onClick = {
                navController?.navigate(Screen.TestingScreen2.Route)
            }) {
                Text(text = "Page 2 ->")
            }
        }
    }
}