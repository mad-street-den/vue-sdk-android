package com.msd.vuesampleapp.presenter.test_screens.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.msd.vuesampleapp.repository.SDKGenericRepository
import com.msd.vuesampleapp.repository.TrackUserRepository

@Composable
fun TrackingOptions1() {
    val trackUserRepository = TrackUserRepository()
    val sdkGenericRepository = SDKGenericRepository()

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
    val sdkGenericRepository = SDKGenericRepository()
    val uuid:MutableState<String?> = remember {
        mutableStateOf(null)
    }
val context = LocalContext.current

    Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceEvenly) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { uuid.value = sdkGenericRepository.displayBloxUUID()

                Toast.makeText(context,uuid.value.toString(),Toast.LENGTH_LONG).show()
            }) {
                Text(text = "Display new ID")
            }

            Button(onClick = { sdkGenericRepository.setNewBloxUUID("DEV's Unique ID") }) {
                Text(text = "Set a new Unique ID")
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { sdkGenericRepository.setNewBloxUUID("DEV's Unique ID") }) {
                Text(text = "Set a new Unique ID")
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

