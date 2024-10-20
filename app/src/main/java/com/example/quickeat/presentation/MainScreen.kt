package com.example.nearpizzajuice.ui

import android.Manifest
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quickeat.presentation.MainViewModel
import com.example.quickeat.presentation.PlaceItem
import com.example.quickeat.R
import com.google.android.gms.location.FusedLocationProviderClient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, fusedLocationClient: FusedLocationProviderClient) {
    var query by remember { mutableStateOf(TextFieldValue("")) }
    val viewModel: MainViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.let {
                    viewModel.setUserLocation(it)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
    Column(modifier = Modifier.padding(16.dp)) {


        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.quickeatlogo),
                contentDescription = "Search Icon",
               modifier = Modifier.padding(start = 80.dp, end = 80.dp, top = 38.dp, bottom = 38.dp), // Set the size of the image
                contentScale = ContentScale.Fit          )
        }
        androidx.compose.material3.Card(
            shape = RoundedCornerShape(26.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 4.dp, end = 4.dp)
                .height(52.dp)
                .background(color = Color.White),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            androidx.compose.material3.TextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                textStyle = TextStyle(
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal
                ),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.White
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { viewModel.searchPlaces(query.text) },
            modifier = Modifier
                .fillMaxWidth()
                .height(43.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF8057BB),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Search",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        val places by viewModel.places.collectAsState()
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(places) { place ->
                PlaceItem(place)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

