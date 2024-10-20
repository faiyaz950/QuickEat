package com.example.quickeat.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.quickeat.data.Location
import com.example.quickeat.data.Place
import com.example.quickeat.ui.theme.customFontFamily

@Composable
fun PlaceItem(place: Place) {
    Card(
        shape = RoundedCornerShape(12.dp),

        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .padding(13.dp)
        ) {

            Text(text = place.name, fontSize = 20.sp, fontWeight = FontWeight.Bold,
                fontFamily = customFontFamily,
                color = Color.Red)

            Spacer(modifier = Modifier.height(8.dp))

            place.categories.forEach { category ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    val iconUrl = "${category.icon.prefix}bg_64${category.icon.suffix}"
                    Image(
                        painter = rememberImagePainter(iconUrl),
                        contentDescription = null,
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(text = category.name,  fontWeight = FontWeight.Normal,
                            fontFamily = customFontFamily,
                            color = Color.Black)
                        Text(text = " ${category.short_name}" ,fontWeight = FontWeight.Normal,
                            fontFamily = customFontFamily,
                            color =Color.Black)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "${place.location.formatted_address}",  fontWeight = FontWeight.Normal,
                fontFamily = customFontFamily,
                color = Color.Black)
            place.location.locality?.let {
                Text(text = "Locality: $it", fontWeight = FontWeight.Normal,
                    fontFamily = customFontFamily,
                    color = Color.Black)
            }
            Text(text = "Country: ${place.location.country}", fontWeight = FontWeight.Normal,
                fontFamily = customFontFamily,
                color = Color.Black)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceItemPreview() {
    val dummyPlace = Place(
        fsq_id = "1",
        name = "Patna Place",
        categories = listOf(),
        location = Location(
            address = "123 Test St",
            country = "India",
            locality = "Patna City",
            postcode = "12345",
            formatted_address = "123 Test St, Patna City, Testland, 12345"
        ),
        distance = 100
    )
    PlaceItem(place = dummyPlace)
}
