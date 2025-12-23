package com.weather.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.weather.domain.model.CityModel
import com.weather.navigation.MainNav
import com.weather.navigation.OnNavigateTo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onNavigateTo: OnNavigateTo
) {
    var searchQuery by remember { mutableStateOf("") }
    val results by viewModel.searchState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Search City") }
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { query ->
                searchQuery = query
                viewModel.searchCity(query)
            },
            label = { Text("Search for a city") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        AnimatedVisibility(visible = results?.isNotEmpty() == true) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                text = "Top Cities"
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                results?.size ?: 0,
            ) { index ->
                val item = results?.get(index)
                if (item != null) {
                    CityItemList(item) {
                        viewModel.saveCity(it.areaName.toString())
                        onNavigateTo(MainNav.MainRout.Home){}
                    }
                }


            }
        }
    }
}

@Composable
fun CityItemList(item: CityModel, onItemClick: (CityModel) -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        border = BorderStroke(1.dp, Color.White),
        shape = RoundedCornerShape(2.dp),
        onClick = { onItemClick(item) }
    ) {

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                Text(text = item.areaName.toString())
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = item.country.toString())
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null
            )
        }
    }
}

@Preview()
@Composable
fun SearchScreenPreview() {
    SearchScreen(onNavigateTo = {_,_->})
}
   
