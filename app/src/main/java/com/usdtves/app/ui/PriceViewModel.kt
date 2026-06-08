package com.usdtves.app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.usdtves.app.data.ExchangeRate
import com.usdtves.app.data.PriceSummary
import com.usdtves.app.data.PriceRepository
import com.usdtves.app.database.PriceHistoryEntity
import com.usdtves.app.network.ExchangeData
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

data class PriceUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val summary: PriceSummary? = null,
    val exchanges: List<ExchangeRate> = emptyList(),
    val allExchanges: List<ExchangeRate> = emptyList(),
    val history: List<PriceHistoryEntity> = emptyList(),
    val isRefreshing: Boolean = false,
    val lastUpdated: String = "",
    val searchQuery: String = "",
)

class PriceViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PriceRepository(application)

    private val _uiState = MutableStateFlow(PriceUiState())
    val uiState: StateFlow<PriceUiState> = _uiState.asStateFlow()

    private var refreshJob: Job? = null

    init {
        loadPrices()
        loadHistory()
        startAutoRefresh()
    }

    private fun startAutoRefresh() {
        refreshJob?.cancel()
        refreshJob = viewModelScope.launch {
            while (isActive) {
                delay(60_000) // Actualizar cada 60 segundos
                loadPrices()
            }
        }
    }

    fun loadPrices() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(
                    isLoading = _uiState.value.summary == null,
                    isRefreshing = true,
                    error = null,
                )

                val rates = repository.getCurrentRates()

                // Filtrar exchanges con datos válidos
                val validExchanges = rates
                    .filter { (_, data) -> data.ask > 0 && data.bid > 0 }
                    .map { (name, data) -> ExchangeRate(name, data) }
                    .sortedBy { it.data.ask }

                if (validExchanges.isEmpty()) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isRefreshing = false,
                        error = "No se encontraron cotizaciones",
                    )
                    return@launch
                }

                val asks = validExchanges.map { it.data.ask }
                val bids = validExchanges.map { it.data.bid }

                val bestAskExchange = validExchanges.minByOrNull { it.data.ask }
                val bestBidExchange = validExchanges.maxByOrNull { it.data.bid }

                val summary = PriceSummary(
                    bestAsk = asks.minOrNull() ?: 0.0,
                    bestAskExchange = bestAskExchange?.name ?: "N/A",
                    bestBid = bids.maxOrNull() ?: 0.0,
                    bestBidExchange = bestBidExchange?.name ?: "N/A",
                    averageAsk = asks.average(),
                    averageBid = bids.average(),
                    spread = asks.average() - bids.average(),
                    exchangeCount = validExchanges.size,
                    exchanges = validExchanges,
                )

                val now = java.text.SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault())
                    .format(java.util.Date())

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isRefreshing = false,
                    summary = summary,
                    exchanges = validExchanges,
                    allExchanges = validExchanges,
                    lastUpdated = now,
                )

                // Guardar registro histórico
                repository.saveDailyRecord(rates)

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isRefreshing = false,
                    error = "Error de conexión: ${e.localizedMessage}",
                )
            }
        }
    }

    fun loadHistory() {
        viewModelScope.launch {
            try {
                val history = repository.getHistory()
                _uiState.value = _uiState.value.copy(history = history)
            } catch (_: Exception) {
                // Silenciar errores de historial
            }
        }
    }

    fun searchExchanges(query: String) {
        val filtered = if (query.isBlank()) {
            _uiState.value.allExchanges
        } else {
            _uiState.value.allExchanges.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
        _uiState.value = _uiState.value.copy(
            searchQuery = query,
            exchanges = filtered,
        )
    }

    override fun onCleared() {
        super.onCleared()
        refreshJob?.cancel()
    }
}
