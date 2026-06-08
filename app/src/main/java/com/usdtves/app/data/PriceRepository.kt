package com.usdtves.app.data

import android.content.Context
import com.usdtves.app.database.AppDatabase
import com.usdtves.app.database.PriceHistoryEntity
import com.usdtves.app.network.ExchangeData
import com.usdtves.app.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PriceRepository(private val context: Context) {

    private val apiService = RetrofitClient.apiService
    private val dao = AppDatabase.getInstance(context).priceHistoryDao()

    /**
     * Obtiene las cotizaciones actuales de la API de CriptoYa
     */
    suspend fun getCurrentRates(): Map<String, ExchangeData> {
        return withContext(Dispatchers.IO) {
            apiService.getUsdtVesRates()
        }
    }

    /**
     * Guarda un registro histórico del día actual
     * Si ya existe un registro para hoy, lo actualiza con promedios
     */
    suspend fun saveDailyRecord(rates: Map<String, ExchangeData>) {
        withContext(Dispatchers.IO) {
            val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            val now = System.currentTimeMillis()

            // Filtrar exchanges con datos válidos
            val validRates = rates.filter { (_, data) ->
                data.ask > 0 && data.bid > 0
            }

            if (validRates.isEmpty()) return@withContext

            val asks = validRates.map { it.value.ask }
            val bids = validRates.map { it.value.bid }

            val avgAsk = asks.average()
            val avgBid = bids.average()
            val minAsk = asks.minOrNull() ?: 0.0
            val maxAsk = asks.maxOrNull() ?: 0.0
            val minBid = bids.minOrNull() ?: 0.0
            val maxBid = bids.maxOrNull() ?: 0.0

            // Encontrar el exchange con mejor precio
            val bestAskEntry = validRates.minByOrNull { it.value.ask }
            val bestBidEntry = validRates.maxByOrNull { it.value.bid }

            val existing = dao.getHistoryByDate(today)

            if (existing != null) {
                // Recalcular promedios incluyendo la nueva muestra
                val totalSamples = existing.sampleCount + 1
                val newAvgAsk = (existing.avgAsk * existing.sampleCount + avgAsk) / totalSamples
                val newAvgBid = (existing.avgBid * existing.sampleCount + avgBid) / totalSamples

                dao.insertOrUpdate(
                    existing.copy(
                        avgAsk = newAvgAsk,
                        avgBid = newAvgBid,
                        minAsk = minOf(existing.minAsk, minAsk),
                        maxAsk = maxOf(existing.maxAsk, maxAsk),
                        minBid = minOf(existing.minBid, minBid),
                        maxBid = maxOf(existing.maxBid, maxBid),
                        bestAskExchange = bestAskEntry?.key ?: existing.bestAskExchange,
                        bestBidExchange = bestBidEntry?.key ?: existing.bestBidExchange,
                        sampleCount = totalSamples,
                        lastUpdatedAt = now,
                    )
                )
            } else {
                dao.insertOrUpdate(
                    PriceHistoryEntity(
                        date = today,
                        avgAsk = avgAsk,
                        avgBid = avgBid,
                        minAsk = minAsk,
                        maxAsk = maxAsk,
                        minBid = minBid,
                        maxBid = maxBid,
                        bestAskExchange = bestAskEntry?.key ?: "N/A",
                        bestBidExchange = bestBidEntry?.key ?: "N/A",
                        sampleCount = 1,
                        lastUpdatedAt = now,
                    )
                )
            }
        }
    }

    /**
     * Obtiene todo el historial de precios
     */
    suspend fun getHistory(): List<PriceHistoryEntity> {
        return withContext(Dispatchers.IO) {
            dao.getAllHistory()
        }
    }

    /**
     * Obtiene los últimos N registros de historial
     */
    suspend fun getRecentHistory(limit: Int = 30): List<PriceHistoryEntity> {
        return withContext(Dispatchers.IO) {
            dao.getRecentHistory(limit)
        }
    }
}
