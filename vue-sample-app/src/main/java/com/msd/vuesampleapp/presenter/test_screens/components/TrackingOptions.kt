package com.msd.vuesampleapp.presenter.test_screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.msd.vuesampleapp.repository.TrackUserRepository

@Composable
fun TrackingOptions1() {
    val trackUserRepository = TrackUserRepository()

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(fontWeight = FontWeight(800), text = "Track Events")
    }
    Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = { trackUserRepository.pageView() },
            ) {
                Text("Track page view")
            }

            Button(onClick = { trackUserRepository.orderConfirmation() }) {
                Text(text = "Order confirmation")
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { trackUserRepository.rightSwipe() }) {
                Text(text = "Track Right swipe")
            }
            Button(onClick = { trackUserRepository.placeOrder() }) {
                Text(text = "Track place order")
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = { trackUserRepository.moduleClick() },
            ) {
                Text("Track Module click")
            }

            Button(onClick = { trackUserRepository.moduleView() }) {
                Text(text = "Track Module view")
            }

        }

    }
}

@Composable
fun TrackingOptions2() {
    val trackUserRepository = TrackUserRepository()


    Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = { trackUserRepository.homePageView() },
            ) {
                Text("Track home page view")
            }

            Button(onClick = { trackUserRepository.leftSwipe() }) {
                Text(text = "Track left swipe")
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = { trackUserRepository.addToCart() },
            ) {
                Text("Track add to cart")
            }

            Button(onClick = { trackUserRepository.removeFromCart() }) {
                Text(text = "Track remove from cart")
            }

        }

    }
}
