package com.msd.vuesampleapp.presenter.test_screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.msd.vuesampleapp.repository.FetchRecommendationRepository

@Composable
fun RecommendationOptionColumn(fetchRecommendationRepository: FetchRecommendationRepository) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { fetchRecommendationRepository.getRecommendationsByPage() },
                modifier = Modifier.weight(1f),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Get Recommendations by Page", textAlign = TextAlign.Center)
            }
            Spacer(modifier = Modifier.width(4.dp))
            Button(
                onClick = { fetchRecommendationRepository.getRecommendationsByPageNSearch() },
                modifier = Modifier.weight(1f),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Get Recommendations by Page and Search", textAlign = TextAlign.Center)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { fetchRecommendationRepository.getRecommendationByModule() },
                modifier = Modifier.weight(1f),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Get Recommendation by Module", textAlign = TextAlign.Center)
            }
            Spacer(modifier = Modifier.width(4.dp))
            Button(
                onClick = { fetchRecommendationRepository.getRecommendationByModuleNSearch() },
                modifier = Modifier.weight(1f),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Get Recommendation by Module and Search", textAlign = TextAlign.Center)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { fetchRecommendationRepository.getRecommendationByStrategy() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Get Recommendation by Strategy")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}