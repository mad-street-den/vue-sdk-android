package com.msd.vuesampleapp.presenter.test_screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONArray

@Composable
fun RecommendationCarousel(carouselItemsFlow: StateFlow<JSONArray?>) {
    val carouselItems = carouselItemsFlow.collectAsState().value
    if (carouselItems != null) {
        val gson = Gson()
        val recommendationListType = object : TypeToken<List<com.msd.vuesampleapp.data.FetchRecommendationResponse>>() {}.type
        val recommendationList = gson.fromJson<List<com.msd.vuesampleapp.data.FetchRecommendationResponse>>(
            carouselItems.toString(),
            recommendationListType
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            recommendationList.forEach { recommendationItem ->
                items(recommendationItem.data) { recommendationData ->
                    RecommendationCarouselCard(recommendationData)
                }
            }
        }
    }
}

@Composable
fun RecommendationCarouselCard(item: com.msd.vuesampleapp.data.FetchRecommendationData) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .padding(8.dp)
    ) {
        AsyncImage(
            model = item.image_link,
            contentDescription = null
        )
        Text(
            text = item.title ?: "",
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}
