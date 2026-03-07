package com.example.finnishpresidents.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finnishpresidents.model.MainViewModel
import com.example.finnishpresidents.ui.theme.FinnishPresidentsTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    Column(modifier = modifier) {
        Text(
            text = "Finnish Presidents",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
        )

        LazyColumn(modifier = modifier) {
            items(viewModel.presidents) { president ->
                PresidentCard(
                    presidentName = president.name,
                    startDuty = president.startDuty,
                    endDuty = president.endDuty,
                    description = president.description,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun PresidentCard(
    presidentName: String,
    startDuty: Int,
    endDuty: Int,
    description: String,
    viewModel: MainViewModel
) {
    var hits by remember { mutableStateOf<Int?>(null) }
    var loading by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                loading = true
                viewModel.fetchWikiHits(presidentName) { result ->
                    hits = result
                    loading = false
                }
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = presidentName,
                modifier = Modifier
                    .padding(bottom = 2.dp),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(text = "$startDuty - $endDuty")
            Text(text = description)
            if (loading) Text("Loading...")
            hits?.let {
                Text(
                    "Wiki Hits: $it",
                    modifier = Modifier
                        .padding(top = 2.dp),
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            if (hits == null && !loading) Text(
                "Click to see Wiki popularity",
                modifier = Modifier,
                color = Color.DarkGray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    FinnishPresidentsTheme {
        MainScreen()
    }
}
