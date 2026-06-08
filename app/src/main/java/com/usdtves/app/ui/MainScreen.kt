package com.usdtves.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel

enum class BottomTab(
    val label: String,
    val icon: ImageVector,
) {
    PRICES("Precios", Icons.Default.CurrencyExchange),
    HISTORY("Historial", Icons.Default.AutoGraph),
}

@Composable
fun MainScreen(
    viewModel: PriceViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var currentTab by remember { mutableStateOf(BottomTab.PRICES) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            NavigationBar {
                BottomTab.entries.forEach { tab ->
                    NavigationBarItem(
                        icon = { Icon(tab.icon, contentDescription = tab.label) },
                        label = { Text(tab.label) },
                        selected = currentTab == tab,
                        onClick = {
                            currentTab = tab
                            if (tab == BottomTab.HISTORY) {
                                viewModel.loadHistory()
                            }
                        },
                    )
                }
            }
        },
    ) { padding ->
        when (currentTab) {
            BottomTab.PRICES -> {
                if (uiState.isLoading && uiState.summary == null) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    PricesTab(
                        uiState = uiState,
                        onRefresh = { viewModel.loadPrices() },
                        onSearch = { viewModel.searchExchanges(it) },
                        modifier = Modifier.padding(padding),
                    )
                }
            }
            BottomTab.HISTORY -> {
                HistoryTab(
                    history = uiState.history,
                    modifier = Modifier.padding(padding),
                )
            }
        }
    }
}
